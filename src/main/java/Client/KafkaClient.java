package Client;

import ErrorLogger.ExceptionParse;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KafkaClient<K, V, R> {
    protected Config.Kafka.KafkaClient config;
    protected Properties consumerProperties;
    protected KafkaConsumer kafkaConsumer;
    protected Map<Integer, TopicPartition> topicPartitionMap = new HashMap<>();
    protected Function<ConsumerRecord<K, V>, R> functor;
    static Logger logger = LoggerFactory.getLogger(KafkaClient.class);

    public KafkaClient(Config.Kafka.KafkaClient config) {
        this.config = config;
        consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", config.getBrokerServer().stream()
                .map(x -> String.format("%s:%s", x.getiP(), x.getPort())).collect(Collectors.joining(",")));
        consumerProperties.put("key.deserializer", config.getDeserializer());
        consumerProperties.put("value.deserializer", config.getDeserializer());
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, config.getAutoCommit());
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getReset());
        consumerProperties.put("group.id", config.getGroupId());
        System.out.println(consumerProperties.get("bootstrap.servers"));
        kafkaConsumer = new KafkaConsumer<K, V>(consumerProperties);
        kafkaConsumer.subscribe(Collections.singleton(config.getTopic()));
    }

    protected TopicPartition adjustOffsetPartition(ConsumerRecord<K, V> record) {
        TopicPartition topicPartition;
        if (!topicPartitionMap.containsKey(record.partition())) {
            topicPartition = new TopicPartition(config.getTopic(), record.partition());
            topicPartitionMap.put(record.partition(), topicPartition);
        } else {
            topicPartition = topicPartitionMap.get(record.partition());
        }
        return topicPartition;
    }

    public Function<ConsumerRecord<K, V>, R> getFunctor() {
        return functor;
    }

    public void setFunctor(Function<ConsumerRecord<K, V>, R> functor) {
        this.functor = functor;
    }

    protected R process(ConsumerRecord<K, V> record) {
        TopicPartition topicPartition = adjustOffsetPartition(record);
        R r = null;
        if (functor != null) {
            try {
                r = functor.apply(record);
                logger.info(String.format("Topic - %s, Partition - %d, Value: %s, OffSet: %s", record.topic(), record.partition(), record.value(), record.offset()));
                kafkaConsumer.commitSync(Collections.singletonMap(topicPartition, new OffsetAndMetadata(record.offset())));
                logger.info(String.format("Topic - %s, Partition - %d, Value: %s committed", record.topic(), record.partition(), record.value()));
            }
            catch (Exception e){
                logger.error(ExceptionParse.toString(e));
            }
        }
        return r;
    }

    public void start() {
        try {
            while (true) {
                ConsumerRecords<K, V> records = kafkaConsumer.poll(Duration.ofSeconds(config.getPollTime()));
                records.forEach(x -> this.process(x));
            }
        } catch (Exception e) {
            logger.error(ExceptionParse.toString(e));
        } finally {
            kafkaConsumer.close();
            logger.info("Kafka Consumer closed");
        }
    }
}
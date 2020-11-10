package Config.Kafka;

import Config.JDBC.JDBCConnection.Address;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KafkaClient {
    private List<Address> brokerServer;
    private String deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
    private String serializer = "org.apache.kafka.common.serialization.StringDeserializer";
    private String autoCommit = "true";
    private String reset = "";
    private String groupId;
    private String topic;
    private String actionKey;
    private long pollTime;

    public long getPollTime() {
        return pollTime;
    }

    public void setPollTime(long pollTime) {
        this.pollTime = pollTime;
    }

    public List<Address> getBrokerServer() {
        return brokerServer;
    }

    public void setBrokerServer(List<Address> brokerServer) {
        this.brokerServer = brokerServer;
    }

    public String getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(String deserializer) {
        this.deserializer = deserializer;
    }

    public String getSerializer() {
        return serializer;
    }

    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }

    public String getAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(String autoCommit) {
        this.autoCommit = autoCommit;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public static void main(String[] args) throws IOException {
        KafkaClient kafkaClient = new KafkaClient();
        kafkaClient.setAutoCommit("false");
        kafkaClient.setGroupId("test");
        kafkaClient.setReset("earliest");
        String local = "localhost";
        List<Address> brokers = new ArrayList<>();
        Address address = new Address();
        address.setiP(local);
        address.setPort(9092);
        brokers.add(address);
        Address address1 = new Address();
        address1.setiP(local);
        address1.setPort(9093);
        brokers.add(address1);
        Address address2 = new Address();
        address2.setiP(local);
        address2.setPort(9094);
        brokers.add(address2);
        kafkaClient.setBrokerServer(brokers);
        kafkaClient.setTopic("test");
        kafkaClient.setActionKey("action");
        kafkaClient.setPollTime(1);
        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);
        FileWriter writer = new FileWriter("./kafkaClient.yaml");
        yaml.dump(kafkaClient, writer);
    }
}

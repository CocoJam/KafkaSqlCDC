import CommandLine.CommandLine;
import Config.JDBC.InitiateSchema.TableSchema;
import Config.JDBC.JDBCConnection.Connection;
import Config.Kafka.KafkaClient;
import DataBaseController.Base.JDBCAction;
import DataBaseController.Base.JDBCBaseController;
import DataBaseController.Mssql.MssqlJDBCController;
import DataQuery.JSONObject.JsonStringToMap;
import ErrorLogger.ExceptionParse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.cli.ParseException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.function.Function;

public class Start {
    static Logger logger = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("This is how you configure Log4J with SLF4J");
        try {
            CommandLine commandLine = new CommandLine(args);
            commandLine.run();
            TableSchema tableSchema = commandLine.getTableSchemaConfig();
            Connection connectionConfig = commandLine.getDBConfig();
            KafkaClient kafkaClientConfig = commandLine.getKafkaClientConfig();
            Map<String, JDBCAction> actionMapping = tableSchema.getActionMapping();
            System.out.println(actionMapping);
            Client.KafkaClient<String, String, Void> kafkaClient = new Client.KafkaClient<String, String, Void>(kafkaClientConfig);
            JDBCBaseController jdbcBaseController = new MssqlJDBCController(connectionConfig, tableSchema);
            Function<ConsumerRecord<String, String>, Void> function = new Function<ConsumerRecord<String, String>, Void>() {
                @Override
                public Void apply(ConsumerRecord<String, String> record) {
                    try {
                        Map<String, Object> jsonObject = JsonStringToMap.convert(record.value().toString());
                        String action = jsonObject.get(kafkaClientConfig.getActionKey()).toString();
                        System.out.println(action);
                        JDBCAction jdbcAction = actionMapping.get(action);
                        switch (jdbcAction){
                            case INSERT:{
                                jdbcBaseController.Insert(jsonObject);
                                break;
                            }
                            case UPDATE:{
                                jdbcBaseController.update(jsonObject,jsonObject);
                                break;
                            }
                            case DELETE:{
                                jdbcBaseController.delete(jsonObject);
                                break;
                            }
                            default:{
                                logger.error("Action mapping is missing for: "+action);
                            }
                        }
                    } catch (JsonProcessingException e) {
                        logger.error(ExceptionParse.toString(e));
                        throw  new RuntimeException(e.getMessage());
                    } catch (Exception e) {
                        logger.error(ExceptionParse.toString(e));
                        throw  new RuntimeException(e.getMessage());
                    }
                    return null;
                }
            };
            kafkaClient.setFunctor(function);
            kafkaClient.start();
        } catch (ParseException e) {
            logger.error(ExceptionParse.toString(e));
        } catch (ClassNotFoundException e) {
            logger.error(ExceptionParse.toString(e));
        } catch (FileNotFoundException e) {
            logger.error(ExceptionParse.toString(e));
        }
    }
}

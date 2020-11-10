package CommandLine;

import Config.JDBC.InitiateSchema.TableSchema;
import Config.JDBC.JDBCConnection.Connection;
import Config.Kafka.KafkaClient;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CommandLine {
    static Logger logger = LoggerFactory.getLogger(CommandLine.class);

    protected Options options;
    protected String[] args;
    protected CommandLineParser parser = new DefaultParser();
    protected org.apache.commons.cli.CommandLine cmd;


    public CommandLine(String[] args) throws ParseException {
        this.args = args;
    }

    public org.apache.commons.cli.CommandLine getCmd() {
        return cmd;
    }

    public Connection getDBConfig() throws FileNotFoundException {
        String c = cmd.getOptionValue("c");
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(c);
        Connection connection = yaml.loadAs(inputStream, Connection.class);
        return connection;
    }

    public KafkaClient getKafkaClientConfig() throws FileNotFoundException {
        String c = cmd.getOptionValue("k");
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(c);
        KafkaClient kafkaClient = yaml.loadAs(inputStream, Config.Kafka.KafkaClient.class);
        return kafkaClient;
    }

    public TableSchema getTableSchemaConfig() throws FileNotFoundException {
        String c = cmd.getOptionValue("t");
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(c);
        TableSchema tableSchema = yaml.loadAs(inputStream, TableSchema.class);
        return tableSchema;
    }

    public void run() throws ParseException {
        Options options = new Options();

        options.addOption(Option.builder("c")
                .argName("DataBaseConnection Config")
                .longOpt("DataBaseConn")
                .hasArg(true)
                .desc("c id ([REQUIRED] or use --DataBaseConn)")
                .required(true)
                .build());

        options.addOption(Option.builder("t")
                .argName("Table Config")
                .longOpt("Table")
                .hasArg(true)
                .desc("t id ([REQUIRED] or use --Table)")
                .required(true)
                .build());

        options.addOption(Option.builder("k")
                .argName("Kafka Config")
                .longOpt("Kafka")
                .hasArg(true)
                .desc("k id ([REQUIRED] or use --Kafka)")
                .required(true)
                .build());
        this.cmd = parser.parse(options, args);
        for (Option option : cmd.getOptions()) {
           logger.info(String.format("ArgName: %s, Value: %s",option.getArgName(), option.getValue()));
        }
    }
}

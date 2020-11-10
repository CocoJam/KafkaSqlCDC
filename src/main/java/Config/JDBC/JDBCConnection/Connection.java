package Config.JDBC.JDBCConnection;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;

public class Connection {
    private String connectionType;
    private Address address;
    private String DataBase;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }


    public String getDataBase() {
        return DataBase;
    }

    public void setDataBase(String dataBase) {
        DataBase = dataBase;
    }

    public static void main(String[] args) throws IOException {
        Connection connection= new Connection();
        connection.setConnectionType("jdbc:sqlserver");
        Address address = new Address();
        address.setiP("localhost");
        address.setPort(1433);
        connection.setDataBase("EOSS");
        connection.setAddress(address);
        User user = new User();
        user.setUsername("SA");
        user.setPassword("Test1234");
        connection.setUser(user);
        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);FileWriter writer = new FileWriter("./connection.yaml");
        yaml.dump(connection, writer);
    }
}

package DataBaseAccess.DataBaseAccessManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataBaseAccessBaseManager implements IDataBaseAccessManager<Connection>{
    private Class<?> clazz;
    private Connection connection;
    private String connectionString;
    private String sqlClassString;

    @Override
    public void close() throws Exception {
        if(connection!=null && !connection.isClosed()){
            connection.close();
        }
        connection = null;
    }

    public SQLDataBaseAccessBaseManager(String sqlClassString, String connectionString) throws ClassNotFoundException {
        this.sqlClassString = sqlClassString;
        this.connectionString = connectionString;
        this.clazz = Class.forName(sqlClassString);
    }

    @Override
    public Connection getConnection() throws Exception {
        if(connection != null){
            this.close();
        }
        connection = DriverManager.getConnection(this.connectionString);
        return connection;
    }

}

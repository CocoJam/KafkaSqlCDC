package DataBaseAccess.DataBaseManager.Sql;

import DataBaseAccess.DataBaseAccessManager.SQLDataBaseAccessBaseManager;
import DataBaseAccess.DataBaseAccessManager.IDataBaseAccessManager;
import DataBaseAccess.DataBaseManager.Base.SQLDataBaseManagerStrategy;

import java.sql.Connection;

public class SQLManagerStrategy extends SQLDataBaseManagerStrategy {
    public SQLManagerStrategy(SQLDataBaseAccessBaseManager connection) {
        super(connection);
    }

    @Override
    public Connection access() throws Exception {
        return connection.getConnection();
    }
}

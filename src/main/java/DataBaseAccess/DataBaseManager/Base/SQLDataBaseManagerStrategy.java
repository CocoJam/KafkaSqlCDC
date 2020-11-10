package DataBaseAccess.DataBaseManager.Base;

import DataBaseAccess.DataBaseAccessManager.IDataBaseAccessManager;
import DataBaseAccess.DataBaseAccessManager.SQLDataBaseAccessBaseManager;

import java.sql.Connection;

public abstract class SQLDataBaseManagerStrategy implements IDataBaseManagerStrategy<Connection> {
    protected SQLDataBaseAccessBaseManager connection;

    public SQLDataBaseManagerStrategy(SQLDataBaseAccessBaseManager connection) {
        this.connection = connection;
    }

    public abstract Connection access() throws Exception;
}

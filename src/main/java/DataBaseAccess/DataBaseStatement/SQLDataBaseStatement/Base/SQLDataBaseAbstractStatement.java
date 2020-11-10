package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.IDataBaseStatement;

import java.sql.*;

public abstract class SQLDataBaseAbstractStatement <T extends Statement,R> implements IDataBaseStatement<T,R> {
    protected T statement;
    protected String query;

    protected SQLDataBaseAbstractStatement() {}

    public SQLDataBaseAbstractStatement(T statement, String query) {
        this.statement = statement;
        this.query = query;
    }

    public abstract T compile() throws Exception;

    public abstract R execute() throws Exception;
}

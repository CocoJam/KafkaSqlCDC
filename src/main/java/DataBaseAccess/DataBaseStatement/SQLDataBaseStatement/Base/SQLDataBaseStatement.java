package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base;

import java.sql.*;

public abstract class SQLDataBaseStatement<O> extends SQLDataBaseAbstractStatement<Statement, O> {

    public SQLDataBaseStatement(Connection connection, String query) throws SQLException {
        super();
        Statement statement = connection.createStatement();
        this.statement = statement;
        this.query = query;
    }

    @Override
    public Statement compile() {
        return statement;
    }
}

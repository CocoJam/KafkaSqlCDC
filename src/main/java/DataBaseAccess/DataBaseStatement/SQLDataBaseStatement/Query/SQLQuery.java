package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBaseStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuery extends SQLDataBaseStatement {
    public SQLQuery(Connection connection, String query) throws SQLException {
        super(connection, query);
    }

    @Override
    public ResultSet execute() throws SQLException {
        return statement.executeQuery(query);
    }

}

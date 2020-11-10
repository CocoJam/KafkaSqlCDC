package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBasePreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedSQLUpdate extends SQLDataBasePreparedStatement {
    public PreparedSQLUpdate(Connection connection, String query) throws SQLException {
        super(connection, query);
    }

    @Override
    public Integer execute() throws Exception {
        PreparedStatement preparedStatement = this.compile();
        return preparedStatement.executeUpdate();
    }

    @Override
    public void addBatch() throws SQLException {
        ((PreparedStatement)this.statement).addBatch();
    }
}

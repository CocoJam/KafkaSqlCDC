package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBasePreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedSQLQuery extends SQLDataBasePreparedStatement {
    public PreparedSQLQuery(Connection connection, String query) throws SQLException {
        super(connection, query);
    }

    @Override
    public ResultSet execute() throws Exception {
        PreparedStatement preparedStatement = this.compile();
//        System.out.println("count: ");
//        System.out.println(preparedStatement.getParameterMetaData());
        return preparedStatement.executeQuery();
    }

    @Override
    public void addBatch() throws SQLException {
        ((PreparedStatement)this.statement).addBatch();
    }
}

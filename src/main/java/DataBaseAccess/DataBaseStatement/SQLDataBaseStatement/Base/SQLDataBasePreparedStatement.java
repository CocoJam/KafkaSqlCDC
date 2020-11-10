package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base;

import DataBaseAccess.DataBaseStatement.SetStatementStrategy.Base.ISetStatementStrategy;
import DataBaseAccess.DataBaseStatement.SetStatementStrategy.SetStatementBaseStrategy;

import java.sql.*;
import java.util.Map;

public abstract class SQLDataBasePreparedStatement<O> extends SQLDataBaseAbstractStatement<PreparedStatement,O> implements IAddBatch {
    protected ISetStatementStrategy<PreparedStatement,O> setStatementStrategy = new SetStatementBaseStrategy<O>();
    protected Map<Integer, O> objectMap;

    public SQLDataBasePreparedStatement(Connection connection, String query) throws SQLException {
        super();
        PreparedStatement statement = connection.prepareStatement(query);
        this.statement = statement;
        this.query = query;
    }

    public void setSetStatementStrategy(ISetStatementStrategy setStatementStrategy) {
        this.setStatementStrategy = setStatementStrategy;
    }

    public void setObjectMap(Map<Integer, O> objectMap) {
        this.objectMap = objectMap;
    }

    @Override
    public PreparedStatement compile() throws Exception {
        for (Map.Entry<Integer, O> integerObjectEntry : this.objectMap.entrySet()) {
            statement = this.setStatementStrategy.setParameter(statement, integerObjectEntry.getKey(), integerObjectEntry.getValue());
        }
        return statement;
    }
}

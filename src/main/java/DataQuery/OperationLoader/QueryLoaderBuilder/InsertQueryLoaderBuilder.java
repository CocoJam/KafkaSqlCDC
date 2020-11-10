package DataQuery.OperationLoader.QueryLoaderBuilder;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBasePreparedStatement;
import DataQuery.OperationLoader.QueryLoader.IQueryCompile;
import DataQuery.OperationLoader.QueryLoader.InsertQueryLoader;

import java.util.Map;

public class InsertQueryLoaderBuilder extends BaseQueryLoaderBuilder<String> {
    protected InsertQueryLoader insertQueryLoader = new InsertQueryLoader();

    public InsertQueryLoaderBuilder(String tableName, Map<String, Object> jsonObject, Map<String, Class<?>> colsToMap) {
        super(tableName, jsonObject, colsToMap);
    }

    @Override
    public IQueryCompile<String> compile() {
        for (Map.Entry<String, Object> jsonObject : this.jsonObject.entrySet()) {
            if (colsToMap.containsKey(jsonObject.getKey())) {
                insertQueryLoader.addColumns(jsonObject.getKey());
            }
        }
        insertQueryLoader.setTable(tableName);
        return insertQueryLoader;
    }

    @Override
    public SQLDataBasePreparedStatement setObjectMap(SQLDataBasePreparedStatement statement) {
        statement.setObjectMap(super.getDataMapping());
        return statement;
    }
}

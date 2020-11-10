package DataQuery.OperationLoader.QueryLoaderBuilder;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBasePreparedStatement;
import DataQuery.OperationLoader.QueryLoader.IQueryCompile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseQueryLoaderBuilder<T> {
    protected final Map<String, Object> jsonObject;
    protected final Map<String, Class<?>> colsToMap;
    protected String tableName;

    public BaseQueryLoaderBuilder(String tableName, Map<String, Object> jsonObject, Map<String, Class<?>> colsToMap) {
        this.tableName = tableName;
        this.jsonObject = new HashMap<>(jsonObject);
        this.colsToMap = new HashMap<>(colsToMap);
    }

    protected Map<String, Integer> getReverseDataMapping() {
        Map<String, Integer> reverseDataMapping = new LinkedHashMap<>();
        int i = 1;
        for (Map.Entry<String, Object> jsonObject : jsonObject.entrySet()) {
            if (colsToMap.containsKey(jsonObject)) {
                reverseDataMapping.put(jsonObject.getKey(), i);
                i++;
            }
        }
        return reverseDataMapping;
    }

    protected Map<Integer, Object> getDataMapping(Map<String, Object> injectData) {
        Map<Integer, Object> updateDataMapping = new LinkedHashMap<>();
        int i = 1;
        for (Map.Entry<String, Object> jsonObject : injectData.entrySet()) {
            if (colsToMap.containsKey(jsonObject.getKey())) {
                updateDataMapping.put(i, jsonObject.getValue());
                i++;
            }
        }
        Map<Integer, Object> dataMapping = getDataMapping(i);
        updateDataMapping.putAll(dataMapping);
        return updateDataMapping;
    }

    protected Map<Integer, Object> getDataMapping(int index) {
        Map<Integer, Object> dataMapping = new LinkedHashMap<>();
        int i = index;
        for (Map.Entry<String, Object> jsonObject : jsonObject.entrySet()) {
            if (colsToMap.containsKey(jsonObject.getKey())) {
                dataMapping.put(i, jsonObject.getValue());
                i++;
            }
        }
        return dataMapping;
    }

    protected Map<Integer, Object> getDataMapping() {
        return getDataMapping(1);
    }

    public SQLDataBasePreparedStatement setObjectMap(SQLDataBasePreparedStatement statement) {
        statement.setObjectMap(getDataMapping());
        return statement;
    }

    public abstract IQueryCompile<T> compile();
}

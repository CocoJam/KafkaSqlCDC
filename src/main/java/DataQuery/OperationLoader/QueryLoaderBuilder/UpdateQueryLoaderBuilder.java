package DataQuery.OperationLoader.QueryLoaderBuilder;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBasePreparedStatement;
import DataQuery.DataNode.WhereNode.WhereDataNode;
import DataQuery.DataNode.WhereNode.WhereDataNodeCompose;
import DataQuery.OperationLoader.QueryLoader.IQueryCompile;
import DataQuery.OperationLoader.QueryLoader.UpdateQueryLoader;

import java.util.*;
import java.util.stream.Collectors;

public class UpdateQueryLoaderBuilder extends BaseQueryLoaderBuilder<String> implements IWhereFilter<String,Void> {

    protected UpdateQueryLoader updateQueryLoader = new UpdateQueryLoader();
    protected Map<String, Object> updateValuesMap;
    protected List<String> whereEscapeList;
    protected WhereFilterEnum whereFilterEnum;
    protected List<WhereDataNode> whereDataNodes = new ArrayList<>();
    public UpdateQueryLoaderBuilder(String tableName, Map<String, Object> jsonObject, Map<String, Class<?>> colsToMap, Map<String, Object> updateValuesMap) {
        super(tableName, jsonObject, colsToMap);
        this.updateValuesMap = updateValuesMap;
    }

    @Override
    public IQueryCompile<String> compile() {
        for (Map.Entry<String, Object> jsonObjectEntry : updateValuesMap.entrySet()) {
            if(colsToMap.containsKey(jsonObjectEntry.getKey())){
                updateQueryLoader.setUpdate(jsonObjectEntry.getKey() + "=?");
            }
        }
        updateQueryLoader.setTable(tableName);
        if(whereEscapeList!=null && whereFilterEnum == WhereFilterEnum.ONLY){
            only();
        }else if (whereEscapeList!=null && whereFilterEnum == WhereFilterEnum.ESCAPE){
            escape();
        }
        WhereDataNode whereDataNodeRoot = null;
        for (WhereDataNode whereDataNode : whereDataNodes) {
            whereDataNodeRoot = WhereDataNodeCompose.AND(whereDataNodeRoot, whereDataNode);
        }
        updateQueryLoader.setWhere(whereDataNodeRoot);
        return updateQueryLoader;
    }

    @Override
    public SQLDataBasePreparedStatement setObjectMap(SQLDataBasePreparedStatement statement) {
        if (updateValuesMap != null) {
            statement.setObjectMap(getDataMapping(updateValuesMap));
        } else {
            statement.setObjectMap(getDataMapping());
        }
        return statement;
    }

    @Override
    public void whereEscapes(List<String> escapes,WhereFilterEnum whereFilterEnum) {
        this.whereEscapeList = escapes;
        this.whereFilterEnum = whereFilterEnum;
    }

    @Override
    public Void only() {
        for (String s : whereEscapeList) {
            if (colsToMap.containsKey(s)) {
                whereDataNodes.add(WhereDataNodeCompose.SINGLE(s+ "=?"));
            }
        }
        return null;
    }

    @Override
    public Void escape() {
        for (Map.Entry<String, Class<?>> entry : colsToMap.entrySet()) {
            if (whereEscapeList.contains(entry.getKey()) && jsonObject.containsKey(entry.getKey())) {
                whereDataNodes.add(WhereDataNodeCompose.SINGLE(entry.getKey()+ "=?"));
            }
        }
        return null;
    }

    @Override
    protected Map<Integer, Object> getDataMapping(Map<String, Object> injectData) {
        Map<Integer, Object> updateDataMapping = new LinkedHashMap<>();
        int i = 1;
        for (Map.Entry<String, Object> jsonObject : injectData.entrySet()) {
            if (colsToMap.containsKey(jsonObject.getKey()) && updateValuesMap.containsKey(jsonObject.getKey())) {
                updateDataMapping.put(i, jsonObject.getValue());
                i++;
            }
        }
        Map<Integer, Object> dataMapping = getDataMapping(i);
        updateDataMapping.putAll(dataMapping);
        return updateDataMapping;
    }

    @Override
    protected Map<Integer, Object> getDataMapping(int index) {
        Map<Integer, Object> dataMapping = new LinkedHashMap<>();
        int i = index;
        for (Map.Entry<String, Object> jsonObject : jsonObject.entrySet()) {
            if (colsToMap.containsKey(jsonObject.getKey()) && whereEscapeList.contains(jsonObject.getKey())) {
                dataMapping.put(i, jsonObject.getValue());
                i++;
            }
        }
        return dataMapping;
    }
}

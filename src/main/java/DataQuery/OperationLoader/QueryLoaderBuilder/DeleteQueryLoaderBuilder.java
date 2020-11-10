package DataQuery.OperationLoader.QueryLoaderBuilder;

import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base.SQLDataBasePreparedStatement;
import DataQuery.DataNode.WhereNode.WhereDataNode;
import DataQuery.DataNode.WhereNode.WhereDataNodeCompose;
import DataQuery.OperationLoader.QueryLoader.DeleteQueryLoader;
import DataQuery.OperationLoader.QueryLoader.IQueryCompile;

import java.util.*;
import java.util.stream.Collectors;

public class DeleteQueryLoaderBuilder extends BaseQueryLoaderBuilder<String> implements IWhereFilter<String,Void> {
    protected DeleteQueryLoader deleteQueryLoader = new DeleteQueryLoader();
    protected List<String> whereEscapeList;
    protected WhereFilterEnum whereFilterEnum = WhereFilterEnum.None;
    protected List<WhereDataNode> whereDataNodes = new ArrayList<>();

    public DeleteQueryLoaderBuilder(String tableName, Map<String, Object> jsonObject, Map<String, Class<?>> colsToMap) {
        super(tableName, jsonObject, colsToMap);
    }

    @Override
    public IQueryCompile<String> compile() {
        deleteQueryLoader.setTable(tableName);
        if(whereEscapeList!=null && whereFilterEnum == WhereFilterEnum.ONLY){
            only();
        }else if (whereEscapeList!=null && whereFilterEnum == WhereFilterEnum.ESCAPE){
            escape();
        }
        WhereDataNode whereDataNodeRoot = null;
        for (WhereDataNode whereDataNode : whereDataNodes) {
            whereDataNodeRoot = WhereDataNodeCompose.AND(whereDataNodeRoot, whereDataNode);
        }
        deleteQueryLoader.setWhere(whereDataNodeRoot);
        return deleteQueryLoader;
    }

    @Override
    public SQLDataBasePreparedStatement setObjectMap(SQLDataBasePreparedStatement statement) {
        statement.setObjectMap(getDataMapping());
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
    protected Map<Integer, Object> getDataMapping(int index) {
        Map<Integer, Object> dataMapping = new LinkedHashMap<>();
        int i = index;
        for (Map.Entry<String, Object> jsonObject : jsonObject.entrySet()) {
            if (whereEscapeList.contains(jsonObject.getKey()) && colsToMap.containsKey(jsonObject.getKey())) {
                dataMapping.put(i, jsonObject.getValue());
                i++;
            }
        }
        return dataMapping;
    }

    @Override
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

    @Override
    protected Map<Integer, Object> getDataMapping() {
        return getDataMapping(1);
    }
}

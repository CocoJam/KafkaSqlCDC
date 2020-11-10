package DataQuery.OperationLoader.QueryLoader;

import DataQuery.DataNode.ColumnsNode.ColumnDataNode;
import DataQuery.DataNode.ColumnsNode.ColumnJointDataNode;
import DataQuery.DataNode.ColumnsNode.ISetColumns;
import DataQuery.DataNode.InsertNode.ColValueJointDataNode;
import DataQuery.DataNode.InsertNode.InsertDataNode;
import DataQuery.DataNode.InsertNode.ValuesDataNode;
import DataQuery.DataNode.InsertNode.ValuesJointDataNode;
import DataQuery.DataNode.TableNode.ISetTable;
import DataQuery.DataNode.TableNode.TableDataNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class InsertQueryLoader implements ISetColumns<InsertQueryLoader, String>, ISetTable<InsertQueryLoader, String>,
        IQueryCompile<String>{
    protected InsertDataNode insertDataNode = new InsertDataNode();
    protected TableDataNode tableDataNode = null;
    protected ColumnJointDataNode insertColsNode = new ColumnJointDataNode();
    protected ColValueJointDataNode colValueJointDataNode = new ColValueJointDataNode("Values");
    protected ValuesJointDataNode valuesJointDataNode = new ValuesJointDataNode("VALUES");
    protected Map<String, ColumnDataNode> colsDataNodes = new LinkedHashMap<>();
    public static void main(String[] args) {
        InsertDataNode insertDataNode = new InsertDataNode();
        TableDataNode intoTable = new TableDataNode("table_name");
        ColumnJointDataNode insertColsNode = new ColumnJointDataNode();
        ColValueJointDataNode colValueJointDataNode = new ColValueJointDataNode("Values");
        ValuesJointDataNode valuesJointDataNode = new ValuesJointDataNode("VALUES");
        ValuesDataNode valuesDataNode = new ValuesDataNode("?");
        ColumnDataNode insertDataNode2 = new ColumnDataNode("cols_1");
        ColumnDataNode insertDataNode3 = new ColumnDataNode("cols_2");
        insertDataNode.append(intoTable);
        insertDataNode.append(colValueJointDataNode);
        insertDataNode.append(valuesJointDataNode);
        colValueJointDataNode.append(insertColsNode);
        insertColsNode.append(insertDataNode2);
        insertColsNode.append(insertDataNode3);
        for (int i = 0; i < 2; i++) {
            valuesJointDataNode.append(valuesDataNode);
        }
        System.out.println(insertDataNode.compile());

        InsertQueryLoader insertQueryLoader = new InsertQueryLoader();
        insertQueryLoader.addColumns("col1");
        insertQueryLoader.addColumns("col2");
        insertQueryLoader.addColumns("col3");
        insertQueryLoader.addColumns("col4");

        insertQueryLoader.setTable("table_names");
        System.out.println(insertQueryLoader.compile());
    }

    @Override
    public InsertQueryLoader addColumns(String cols) {
        colsDataNodes.put(cols, new ColumnDataNode(cols));
        return this;
    }

    @Override
    public InsertQueryLoader setTable(String table) {
        if(tableDataNode == null){
            tableDataNode = new TableDataNode(table);
        }else{
            throw new RuntimeException("Table node has been provided");
        }
        return this;
    }

    @Override
    public String compile() {
        insertDataNode.append(tableDataNode);
        insertDataNode.append(colValueJointDataNode);
        insertDataNode.append(valuesJointDataNode);
        colValueJointDataNode.append(insertColsNode);
        ValuesDataNode valuesDataNode = new ValuesDataNode("?");
        for (Map.Entry<String, ColumnDataNode> entry : colsDataNodes.entrySet()) {
            valuesJointDataNode.append(valuesDataNode);
            insertColsNode.append(entry.getValue());
        }
        return insertDataNode.compile();
    }
}

package DataQuery.OperationLoader.QueryLoader;

import DataQuery.DataNode.ColumnsNode.AggregateDataNode;
import DataQuery.DataNode.ColumnsNode.ColumnDataNode;
import DataQuery.DataNode.ColumnsNode.ColumnJointDataNode;
import DataQuery.DataNode.DeleteNode.DeleteDataNode;
import DataQuery.DataNode.InsertNode.ColValueJointDataNode;
import DataQuery.DataNode.InsertNode.InsertDataNode;
import DataQuery.DataNode.InsertNode.ValuesDataNode;
import DataQuery.DataNode.InsertNode.ValuesJointDataNode;
import DataQuery.DataNode.SelectNode.SelectDataNode;
import DataQuery.DataNode.TableNode.TableDataNode;
import DataQuery.DataNode.UpdateNode.SetJointNode;
import DataQuery.DataNode.UpdateNode.UpdateDataNode;
import DataQuery.DataNode.WhereNode.WhereDataNode;
import DataQuery.DataNode.WhereNode.WhereJointNode;

public class DataNodeLoader {
    public static void main(String[] args) {
        SelectDataNode dataNode = new SelectDataNode();
        ColumnJointDataNode colsNode = new ColumnJointDataNode();
        ColumnDataNode dataNode2 = new ColumnDataNode("cols_1");
        ColumnDataNode dataNode3 = new ColumnDataNode("cols_2");
        ColumnDataNode dataNode4 = new ColumnDataNode("cols_3");
        TableDataNode fromTable = new TableDataNode("from");
        TableDataNode dataNode5 = new TableDataNode("Table_name");
        AggregateDataNode sum = new AggregateDataNode("SUM");

        WhereJointNode whereJointNode = new WhereJointNode();

        WhereDataNode whereNodeand1 = new WhereDataNode("And");
        WhereDataNode whereNode3 = new WhereDataNode("cols_3=?");


        WhereDataNode whereNode = new WhereDataNode("OR");
        WhereDataNode whereNode1 = new WhereDataNode("cols_1=?");
        WhereDataNode whereNode2 = new WhereDataNode("cols_2=?");

        dataNode.append(colsNode);
        dataNode.append(fromTable);
        dataNode.append(whereJointNode);
        colsNode.append(dataNode2);
        colsNode.append(sum);
        sum.append(dataNode3);
        sum.append(dataNode4);
        fromTable.append(dataNode5);

        whereJointNode.append(whereNodeand1);

        whereNodeand1.append(whereNode3);
        whereNodeand1.append(whereNode);

        whereNode.append(whereNode1);
        whereNode.append(whereNode2);

        System.out.println(dataNode.compile());


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

        UpdateDataNode updateDataNode = new UpdateDataNode();
        TableDataNode updateTable = new TableDataNode("table_name");
        SetJointNode setJointNode = new SetJointNode();
        ValuesDataNode updateValuesDataNode1 = new ValuesDataNode("col1=?");
        ValuesDataNode updateValuesDataNode2 = new ValuesDataNode("col2=?");
        updateDataNode.append(updateTable);
        updateDataNode.append(setJointNode);
        setJointNode.append(updateValuesDataNode1);
        setJointNode.append(updateValuesDataNode2);

        System.out.println(updateDataNode.compile());

        DeleteDataNode deleteDataNode = new DeleteDataNode();
        TableDataNode deleteFromTableDataNode = new TableDataNode("from");
        TableDataNode deleteTableDataNode = new TableDataNode("Table_name");

        deleteDataNode.append(deleteFromTableDataNode);
        deleteFromTableDataNode.append(deleteTableDataNode);
        deleteDataNode.append(whereJointNode);
        System.out.println(deleteDataNode.compile());
    }
}

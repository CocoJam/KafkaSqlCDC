package DataQuery.OperationLoader.QueryLoader;

import DataQuery.DataNode.InsertNode.ValuesDataNode;
import DataQuery.DataNode.TableNode.ISetTable;
import DataQuery.DataNode.TableNode.TableDataNode;
import DataQuery.DataNode.UpdateNode.ISetUpdate;
import DataQuery.DataNode.UpdateNode.SetJointNode;
import DataQuery.DataNode.UpdateNode.UpdateDataNode;
import DataQuery.DataNode.WhereNode.ISetWhere;
import DataQuery.DataNode.WhereNode.WhereDataNode;
import DataQuery.DataNode.WhereNode.WhereDataNodeCompose;
import DataQuery.DataNode.WhereNode.WhereJointNode;

import java.util.ArrayList;
import java.util.List;

public class UpdateQueryLoader implements ISetTable<UpdateQueryLoader, String>, ISetUpdate<UpdateQueryLoader,String>, ISetWhere<UpdateQueryLoader, WhereDataNode>,
        IQueryCompile<String>{
    protected UpdateDataNode updateDataNode = new UpdateDataNode();
    protected TableDataNode tableDataNode = null;
    protected SetJointNode setJointNode = new SetJointNode();
    protected List<ValuesDataNode> valuesDataNodes = new ArrayList<>();
    protected WhereJointNode whereJointNode = new WhereJointNode();
    protected WhereDataNode whereDataNode = null;

    public static void main(String[] args) {
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

        UpdateQueryLoader updateQueryLoader = new UpdateQueryLoader();
        updateQueryLoader.setTable("table_name");
        updateQueryLoader.setUpdate("col1=?");
        updateQueryLoader.setUpdate("col2=?");
        updateQueryLoader.setUpdate("col3=?");
        updateQueryLoader.setUpdate("col4=?");

        WhereDataNode whereDataNode = WhereDataNodeCompose.AND("cols_3=?",WhereDataNodeCompose.AND("cols_1=?","cols_2=?"));
        updateQueryLoader.setWhere(whereDataNode);
        System.out.println(updateQueryLoader.compile());
    }

    @Override
    public String compile() {
        updateDataNode.append(tableDataNode);
        updateDataNode.append(setJointNode);

        for (ValuesDataNode valuesDataNode : valuesDataNodes) {
            setJointNode.append(valuesDataNode);
        }

        if(this.whereDataNode != null){
            updateDataNode.append(whereJointNode);
//            for (WhereDataNode whereDataNode : whereDataNodes) {
            whereJointNode.append(whereDataNode);
//            }
        }
        return updateDataNode.compile();
    }

    @Override
    public UpdateQueryLoader setTable(String table) {
        if (tableDataNode == null) {
            tableDataNode = new TableDataNode(table);
        } else {
            throw new RuntimeException("Table node has been provided");
        }
        return this;
    }

    @Override
    public UpdateQueryLoader setWhere(WhereDataNode whereDataNode) {
        this.whereDataNode = whereDataNode;
        return this;
    }

    @Override
    public UpdateQueryLoader setUpdate(String s) {
        this.valuesDataNodes.add(new ValuesDataNode(s));
        return this;
    }
}

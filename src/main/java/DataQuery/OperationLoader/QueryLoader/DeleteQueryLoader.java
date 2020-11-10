package DataQuery.OperationLoader.QueryLoader;

import DataQuery.DataNode.DeleteNode.DeleteDataNode;
import DataQuery.DataNode.TableNode.ISetTable;
import DataQuery.DataNode.TableNode.TableDataNode;
import DataQuery.DataNode.WhereNode.ISetWhere;
import DataQuery.DataNode.WhereNode.WhereDataNode;
import DataQuery.DataNode.WhereNode.WhereDataNodeCompose;
import DataQuery.DataNode.WhereNode.WhereJointNode;

public class DeleteQueryLoader implements ISetTable<DeleteQueryLoader, String>, ISetWhere<DeleteQueryLoader, WhereDataNode>,
        IQueryCompile<String> {

    protected DeleteDataNode deleteDataNode = new DeleteDataNode();
    protected TableDataNode fromTableDataNode = new TableDataNode("from");
    protected WhereJointNode whereJointNode = new WhereJointNode();
    protected WhereDataNode whereDataNode = null;
    protected TableDataNode tableDataNode = null;

    public static void main(String[] args) {
        DeleteDataNode deleteDataNode = new DeleteDataNode();
        TableDataNode deleteFromTableDataNode = new TableDataNode("from");
        TableDataNode deleteTableDataNode = new TableDataNode("Table_name");

        WhereJointNode whereJointNode = new WhereJointNode();

        WhereDataNode whereNodeand1 = new WhereDataNode("And");
        WhereDataNode whereNode3 = new WhereDataNode("cols_3=?");


        WhereDataNode whereNode = new WhereDataNode("OR");
        WhereDataNode whereNode1 = new WhereDataNode("cols_1=?");
        WhereDataNode whereNode2 = new WhereDataNode("cols_2=?");


//        WhereDataNode whereDataNode = WhereDataNodeCompose.AND("cols_3=?",WhereDataNodeCompose.OR("cols_1=?","cols_2=?"));
        WhereDataNode whereDataNode = WhereDataNodeCompose.AND("cols_3=?",WhereDataNodeCompose.AND("cols_1=?","cols_2=?"));

        whereJointNode.append(whereDataNode);

//        whereNodeand1.append(whereNode3);
//        whereNodeand1.append(whereNode);
//
//        whereNode.append(whereNode1);
//        whereNode.append(whereNode2);

        deleteDataNode.append(deleteFromTableDataNode);
        deleteFromTableDataNode.append(deleteTableDataNode);
        deleteDataNode.append(whereJointNode);
        System.out.println(deleteDataNode.compile());


        DeleteQueryLoader deleteQueryLoader = new DeleteQueryLoader();
        deleteQueryLoader.setTable("table_name");
//        WhereDataNode whereDataNode = WhereDataNodeCompose.AND("cols_3=?",WhereDataNodeCompose.OR("cols_1=?","cols_2==?"));
//        deleteQueryLoader.setWhere("cols=?");
//        deleteQueryLoader.setWhere("cols1=?");
        deleteQueryLoader.setWhere(whereDataNode);

        System.out.println(deleteQueryLoader.compile());
    }

    @Override
    public String compile() {
        deleteDataNode.append(fromTableDataNode);
        fromTableDataNode.append(tableDataNode);
        if(this.whereDataNode != null){
            deleteDataNode.append(whereJointNode);
//            for (WhereDataNode whereDataNode : whereDataNodes) {
                whereJointNode.append(whereDataNode);
//            }
        }
        return deleteDataNode.compile();
    }

    @Override
    public DeleteQueryLoader setTable(String table) {
        if (tableDataNode == null) {
            tableDataNode = new TableDataNode(table);
        } else {
            throw new RuntimeException("Table node has been provided");
        }
        return this;
    }

    @Override
    public DeleteQueryLoader setWhere(WhereDataNode whereDataNode) {
        this.whereDataNode = whereDataNode;
        return this;
    }
}

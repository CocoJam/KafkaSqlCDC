package DataQuery.OperationLoader.QueryLoader;

import DataQuery.DataNode.ColumnsNode.*;
import DataQuery.DataNode.SelectNode.SelectDataNode;
import DataQuery.DataNode.TableNode.ISetTable;
import DataQuery.DataNode.TableNode.TableDataNode;
import DataQuery.DataNode.WhereNode.ISetWhere;
import DataQuery.DataNode.WhereNode.WhereDataNode;
import DataQuery.DataNode.WhereNode.WhereDataNodeCompose;
import DataQuery.DataNode.WhereNode.WhereJointNode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectQueryLoader implements ISetColumns<SelectQueryLoader, String>, ISetAggregateColumns<SelectQueryLoader, String>,
        ISetTable<SelectQueryLoader, String>, ISetWhere<SelectQueryLoader, WhereDataNode> ,IQueryCompile<String>{
    protected SelectDataNode selectDataNode = new SelectDataNode();
    protected ColumnJointDataNode colsNode = new ColumnJointDataNode();
    protected TableDataNode fromTableDataNode = new TableDataNode("from");
    protected WhereJointNode whereJointNode = new WhereJointNode();
    protected Map<String, ColumnDataNode> colsDataNodes = new HashMap<>();
    protected Map<String, AggregateDataNode> aggregateDataNodes = new HashMap<>();
    protected WhereDataNode whereDataNode = null;
    protected TableDataNode tableDataNode = null;

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

        SelectQueryLoader selectQueryLoader = new SelectQueryLoader();
        selectQueryLoader.addColumns("cols1");
        selectQueryLoader.addColumns("cols2");
        selectQueryLoader.addColumns("cols3");

        selectQueryLoader.addAggregateColumns("cols2", "sum");

        selectQueryLoader.setTable("tables_name");
        selectQueryLoader.setWhere(WhereDataNodeCompose.SINGLE("col1=?"));

        System.out.println(selectQueryLoader.compile());

        Pattern pattern = Pattern.compile("([^ ()]*( +)?)(?=(\\=( +)?\\?))", Pattern.CASE_INSENSITIVE);
        String querySample = "SELECT cols_1 ,SUM(cols_2 ,cols_3 )  from Table_name  WHERE  (cols_3= ?  And ( cols_1=?  OR cols_2=   ?    )   )";

        Matcher matcher = pattern.matcher(querySample);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Override
    public SelectQueryLoader addAggregateColumns(String cols, String agg) {
        if(colsDataNodes.containsKey(cols)){
            aggregateDataNodes.put(cols, new AggregateDataNode(agg));
        }else{
            throw new RuntimeException("column not contained "+ cols+" for aggregation");
        }
        return this;
    }

    @Override
    public SelectQueryLoader addColumns(String cols) {
        colsDataNodes.put(cols, new ColumnDataNode(cols));
        return this;
    }

    @Override
    public SelectQueryLoader setTable(String table) {
        if(tableDataNode == null){
            tableDataNode = new TableDataNode(table);
        }else{
            throw new RuntimeException("Table node has been provided");
        }
        return this;
    }

    @Override
    public String compile() {
        selectDataNode.append(colsNode);

        for (Map.Entry<String, ColumnDataNode> entry : colsDataNodes.entrySet()) {
            if(aggregateDataNodes.containsKey(entry.getKey())){
                AggregateDataNode aggregateDataNode = aggregateDataNodes.get(entry.getKey());
                aggregateDataNode.append(entry.getValue());
                colsNode.append(aggregateDataNode);
            }else{
                colsNode.append(entry.getValue());
            }
        }

        selectDataNode.append(fromTableDataNode);
        fromTableDataNode.append(tableDataNode);

        if(this.whereDataNode != null){
            selectDataNode.append(whereJointNode);
//            for (WhereDataNode whereDataNode : whereDataNodes) {
            whereJointNode.append(whereDataNode);
//            }
        }

        return selectDataNode.compile();
    }

    @Override
    public SelectQueryLoader setWhere(WhereDataNode whereDataNode) {
        this.whereDataNode = whereDataNode;
        return this;
    }
}

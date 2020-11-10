package DataQuery.DataNode.InsertNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;
import DataQuery.DataNode.ColumnsNode.ColumnDataNode;

public class ValuesDataNode<V> extends DataNode<ColumnDataNode,String> implements IJointNode {
    public ValuesDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public ValuesDataNode(String value) {
        super(value);
    }

    @Override
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append(this.getValue());
        stringBuilder.append(" ");
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.compile());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        String str = stringBuilder.toString();
        return str+" ";
    }
}

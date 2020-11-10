package DataQuery.DataNode.ColumnsNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;

public class ColumnJointDataNode extends DataNode<ColumnDataNode,String> implements IJointNode {
    public ColumnJointDataNode(IDataNode parent) {
        super(parent,"");
    }

    public ColumnJointDataNode() {
        super("");
    }

    @Override
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.compile());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        String str = stringBuilder.toString();
        return str.trim()+" ";
    }
}

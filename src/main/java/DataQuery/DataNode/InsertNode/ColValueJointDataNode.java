package DataQuery.DataNode.InsertNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;
import DataQuery.DataNode.ColumnsNode.ColumnJointDataNode;

public class ColValueJointDataNode extends DataNode<ColumnJointDataNode,String> implements IJointNode {
    public ColValueJointDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public ColValueJointDataNode(String value) {
        super(value);
    }
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.compile());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}

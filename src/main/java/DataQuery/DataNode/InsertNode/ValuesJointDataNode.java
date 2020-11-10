package DataQuery.DataNode.InsertNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;

public class ValuesJointDataNode extends DataNode<ValuesDataNode,String> implements IJointNode {

    public ValuesJointDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public ValuesJointDataNode(String value) {
        super(value);
    }

    @Override
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append(this.getValue());
        stringBuilder.append(" ");
        stringBuilder.append("(");
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.compile());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(")");
        String str = stringBuilder.toString();
        return str;
    }

}

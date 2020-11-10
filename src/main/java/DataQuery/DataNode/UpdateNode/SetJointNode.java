package DataQuery.DataNode.UpdateNode;

import DataQuery.DataNode.Base.*;
import DataQuery.DataNode.InsertNode.ValuesDataNode;

public class SetJointNode extends DataNode<ValuesDataNode,String> implements IJointNode  {
    public SetJointNode(IDataNode parent) {
        super(parent, Action.SET.toString());
    }

    public SetJointNode() {
        super(Action.SET.toString());
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
        return str;
    }

}

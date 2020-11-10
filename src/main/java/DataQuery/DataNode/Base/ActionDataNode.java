package DataQuery.DataNode.Base;

public class ActionDataNode extends DataNode<IDataNode,String> implements IJointNode{

    public ActionDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public ActionDataNode(String value) {
        super(value);
    }

    @Override
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.value);
        stringBuilder.append(" ");
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.compile());
        }
        return stringBuilder.toString();
    }
}

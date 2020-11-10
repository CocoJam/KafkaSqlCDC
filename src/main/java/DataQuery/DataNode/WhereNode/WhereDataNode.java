package DataQuery.DataNode.WhereNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;

public class WhereDataNode extends DataNode<WhereDataNode,String> implements IJointNode{
    public WhereDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public WhereDataNode(String value) {
        super(value);
    }

    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.dataNodes.size() == 0){
            stringBuilder.append(this.value);
            stringBuilder.append(" ");
            return stringBuilder.toString();
        }
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.compile());
            stringBuilder.append(" ");
            stringBuilder.append(this.value);
            stringBuilder.append(" ");
        }
        stringBuilder.delete(stringBuilder.length()-1-this.value.length(),stringBuilder.length()-1);
//        stringBuilder.trimToSize();
        stringBuilder.insert(0,"( ");
        stringBuilder.append(" )");
        return stringBuilder.toString().trim();
    }
}

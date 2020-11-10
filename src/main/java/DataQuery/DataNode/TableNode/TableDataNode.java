package DataQuery.DataNode.TableNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;

public class TableDataNode extends DataNode<TableDataNode,String> implements IJointNode {
    public TableDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public TableDataNode(String value) {
        super(value);
    }

    @Override
    public String compile() {
        if(this.dataNodes.size() > 1){
            throw new RuntimeException("More than one table");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append(this.value);
        stringBuilder.append(" ");
        for (IDataNode dataNode : this.dataNodes) {
            stringBuilder.append(dataNode.getValue());
            stringBuilder.append(" ");
        }
        String str = stringBuilder.toString();
        return str;
    }
}

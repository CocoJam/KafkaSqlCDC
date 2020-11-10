package DataQuery.DataNode.ColumnsNode;

import DataQuery.DataNode.Base.DataNode;
import DataQuery.DataNode.Base.IDataNode;
import DataQuery.DataNode.Base.IJointNode;

public class ColumnDataNode extends DataNode<ColumnDataNode,String> implements IJointNode {
    public ColumnDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public ColumnDataNode(String value) {
        super(value);
    }

    @Override
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.dataNodes.isEmpty()){
//            stringBuilder.append("\'");
            stringBuilder.append(this.getValue());
//            stringBuilder.append("\'");
        }else{
            for (int i = 0; i < this.dataNodes.size(); i++) {
                stringBuilder.append(this.dataNodes.get(i).compile());
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        String str = stringBuilder.toString();
        return str+" ";
    }
}

package DataQuery.DataNode.ColumnsNode;

import DataQuery.DataNode.Base.IDataNode;

public class AggregateDataNode extends ColumnDataNode {
    public AggregateDataNode(IDataNode parent, String value) {
        super(parent, value);
    }

    public AggregateDataNode(String value) {
        super(value);
    }

    @Override
    public String compile() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.dataNodes.isEmpty()) {
            stringBuilder.append(this.getValue());
        } else {
            stringBuilder.append(this.getValue());
            stringBuilder.append("(");
            for (int i = 0; i < this.dataNodes.size(); i++) {
                stringBuilder.append(this.dataNodes.get(i).compile());
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        String str = stringBuilder.toString();
        return str+") ";
    }

}

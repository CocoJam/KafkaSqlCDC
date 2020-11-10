package DataQuery.DataNode.InsertNode;

import DataQuery.DataNode.Base.*;

public class InsertDataNode  extends ActionDataNode {
    public InsertDataNode(IDataNode parent) {
        super(parent, Action.INSERT.toString());
    }

    public InsertDataNode() {
        super(Action.INSERT.toString());
    }
}

package DataQuery.DataNode.SelectNode;

import DataQuery.DataNode.Base.*;

public class SelectDataNode extends ActionDataNode {
    public SelectDataNode(IDataNode parent) {
        super(parent, Action.SELECT.toString());
    }

    public SelectDataNode() {
        super(Action.SELECT.toString());
    }
}

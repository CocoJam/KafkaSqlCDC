package DataQuery.DataNode.UpdateNode;

import DataQuery.DataNode.Base.*;

public class UpdateDataNode extends ActionDataNode {
    public UpdateDataNode(IDataNode parent) {
        super(parent, Action.UPDATE.toString());
    }

    public UpdateDataNode() {
        super(Action.UPDATE.toString());
    }
}

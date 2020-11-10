package DataQuery.DataNode.DeleteNode;

import DataQuery.DataNode.Base.Action;
import DataQuery.DataNode.Base.ActionDataNode;
import DataQuery.DataNode.Base.IDataNode;

public class DeleteDataNode extends ActionDataNode {
    public DeleteDataNode(IDataNode parent) {
        super(parent, Action.DELETE.toString());
    }

    public DeleteDataNode() {
        super(Action.DELETE.toString());
    }
}

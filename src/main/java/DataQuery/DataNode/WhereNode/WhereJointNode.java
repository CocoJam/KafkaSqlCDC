package DataQuery.DataNode.WhereNode;

import DataQuery.DataNode.Base.*;

public class WhereJointNode extends ActionDataNode {
    public WhereJointNode(IDataNode parent) {
        super(parent, Action.WHERE.toString());
    }

    public WhereJointNode() {
        super(Action.WHERE.toString());
    }
}

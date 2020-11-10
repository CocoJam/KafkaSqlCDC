package DataQuery.DataNode.Base;

import java.util.ArrayList;
import java.util.List;

public class DataNode<D extends IDataNode,V> implements IDataNode<D,V>{
    protected V value;
    protected IDataNode parent;
    protected List<D> dataNodes;

    public DataNode(IDataNode parent,V value) {
        this.value = value;
        dataNodes = new ArrayList<>();
    }

    @Override
    public V getValue() {
        return value;
    }

    public DataNode(V value) {
        this.value = value;
        dataNodes = new ArrayList<>();
    }

    @Override
    public void append(D i) {
        i.setParent(this);
        dataNodes.add(i);
    }

    @Override
    public List<D> traverse() {
        return dataNodes;
    }

    @Override
    public IDataNode parent() {
        return parent;
    }

    @Override
    public void setParent(D i) {
        this.parent = i;
    }

    @Override
    public String toString() {
        return "DataNode{" +
                "value=" + value +
                ", parent=" + parent +
//                ", dataNodes=" + dataNodes +
                '}';
    }

    public V compile(){
        return this.value;
    };
}

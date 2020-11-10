package DataQuery.DataNode.Base;

import java.util.List;

public interface IDataNode<D extends IDataNode,V> {
    public void append(D i);
    public List<D> traverse();
    public IDataNode parent();
    public void setParent(D i);
    public V getValue();
    public V compile();
}

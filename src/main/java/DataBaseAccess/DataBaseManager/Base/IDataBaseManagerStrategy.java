package DataBaseAccess.DataBaseManager.Base;

import DataBaseAccess.DataBaseAccessManager.IDataBaseAccessManager;

public interface IDataBaseManagerStrategy<M> {
    public M access() throws Exception;
}

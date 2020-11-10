package DataBaseAccess.DataBaseAccessManager;

public interface IDataBaseAccessManager <C> extends AutoCloseable{
    public C getConnection() throws Exception;
}

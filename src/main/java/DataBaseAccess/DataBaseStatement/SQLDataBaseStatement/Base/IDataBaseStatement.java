package DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Base;

public interface IDataBaseStatement<S,R> {
    public S compile() throws Exception;
    public R execute() throws Exception;
}

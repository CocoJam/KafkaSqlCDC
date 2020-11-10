package DataQuery.OperationLoader.QueryLoaderBuilder;

import java.util.List;

public interface IWhereFilter <T,R>{

    public void whereEscapes(List<T> escapes,WhereFilterEnum whereFilterEnum);
    public R only();
    public R escape();
//    public R none();
}

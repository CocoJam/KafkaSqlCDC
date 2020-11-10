package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Mssql;

import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base.IResultDataTypeBaseStrategy;
import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Sql.SqlResultDataTypeStrategy;
import DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Base.ResultClassificationManagerBase;

import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public abstract class MssqlResultClassificationManager<K,R> extends ResultClassificationManagerBase<K,R> {
    protected IResultDataTypeBaseStrategy iResultDataTypeBaseStrategy;

    public MssqlResultClassificationManager(IResultDataTypeBaseStrategy iResultDataTypeBaseStrategy) {
        this.iResultDataTypeBaseStrategy = iResultDataTypeBaseStrategy;
    }

    @Override
    public abstract R classify(ResultSet object, Map<K, Class<?>> map) throws Exception;
}

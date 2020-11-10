package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Base;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;

public abstract class ResultClassificationManagerBase<K,R> implements ISchemaResultClassificationManager<ResultSet,K,R>{
    @Override
    public abstract R classify(ResultSet object, Map<K, Class<?>> map) throws Exception;
}

package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Base;

import java.sql.ResultSet;
import java.util.Map;

public abstract class StringResultClassificationManagerBase<K,R> implements ISchemaResultClassificationManager<Map<String, Object> ,K,R> {
    @Override
    public abstract R classify(Map<String, Object>  object, Map<K, Class<?>> map) throws Exception;
}

package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Base;

import java.util.Map;

public interface ISchemaResultClassificationManager<O,K,R> {
    public R classify(O object, Map<K, Class<?>> map) throws Exception;
}

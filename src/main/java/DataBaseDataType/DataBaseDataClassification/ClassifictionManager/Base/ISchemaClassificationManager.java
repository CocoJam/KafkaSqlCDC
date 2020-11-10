package DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Base;

import java.util.HashMap;
import java.util.Map;

public interface ISchemaClassificationManager {
    public Map.Entry<String,Class<?>> classify(String colName, Integer type);
    public Map.Entry<String,Class<?>> classify(Map.Entry<String, Integer> entry);
    public Map<String, Class<?>> classify(Map<String,Integer> colsMetaData);
}

package DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Mssql;

import DataBaseDataType.DataBaseDataClassification.ClassificationStrategy.Base.IDataTypeBaseStrategy;
import DataBaseDataType.DataBaseDataClassification.ClassificationStrategy.MsSql.MssqlDataTypeStrategy;
import DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Base.ClassificationManagerBase;
import DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Base.ISchemaClassificationManager;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class MssqlClassificationManager extends ClassificationManagerBase  {

    public MssqlClassificationManager(IDataTypeBaseStrategy<Integer,Class<?>> dataTypeBaseStrategy) {
        super(dataTypeBaseStrategy);
    }

    public MssqlClassificationManager() {
        super(new MssqlDataTypeStrategy());
    }

    @Override
    public Class<?> classify(int type) {
        return super.dataTypeBaseStrategy.classify(type);
    }

    @Override
    public Map.Entry<String, Class<?>> classify(String colName, Integer type) {
        return new AbstractMap.SimpleEntry<String, Class<?>>(colName, this.classify(type));
    }

    @Override
    public Map.Entry<String,Class<?>>  classify(Map.Entry<String, Integer> entry) {
        return new AbstractMap.SimpleEntry<String, Class<?>>(entry.getKey(), this.classify(entry.getValue()));
    }

    @Override
    public Map<String, Class<?>> classify(Map<String, Integer> colsMetaData) {
        Map<String,Class<?>> classHashMap = new HashMap<>(colsMetaData.size());
        for (Map.Entry<String, Integer> stringIntegerEntry : colsMetaData.entrySet()) {
            Map.Entry<String,Class<?>> m = this.classify(stringIntegerEntry);
            classHashMap.put(m.getKey(),m.getValue());
        }
        return classHashMap;
    }
}

package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Mssql;

import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base.IResultDataTypeBaseStrategy;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MssqlResultClassificationIntegerManager extends MssqlResultClassificationManager<Integer,Map<Integer,Object>> {
    public MssqlResultClassificationIntegerManager(IResultDataTypeBaseStrategy iResultDataTypeBaseStrategy) {
        super(iResultDataTypeBaseStrategy);
    }

    @Override
    public Map<Integer,Object> classify(ResultSet resultSet, Map<Integer, Class<?>> map) throws Exception {
        HashMap<Integer,Object> data = new HashMap<>(map.size());
        for (Map.Entry<Integer, Class<?>> kClassEntry : map.entrySet()) {
            Object o = iResultDataTypeBaseStrategy.classify(resultSet, kClassEntry.getValue(), kClassEntry.getKey());
            data.put(kClassEntry.getKey(),o);
        }
        return data;
    }
}
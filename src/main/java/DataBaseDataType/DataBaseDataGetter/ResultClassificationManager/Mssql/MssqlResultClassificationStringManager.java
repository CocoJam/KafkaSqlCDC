package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Mssql;

import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base.IResultDataTypeBaseStrategy;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MssqlResultClassificationStringManager extends MssqlResultClassificationManager<String,Map<String,Object>> {

    public MssqlResultClassificationStringManager(IResultDataTypeBaseStrategy iResultDataTypeBaseStrategy) {
        super(iResultDataTypeBaseStrategy);
    }

    @Override
    public Map<String, Object> classify(ResultSet resultSet, Map<String, Class<?>> map) throws Exception {
        HashMap<String,Object> data = new HashMap<>(map.size());
        for (Map.Entry<String, Class<?>> kClassEntry : map.entrySet()) {
            Object o = iResultDataTypeBaseStrategy.classify(resultSet, kClassEntry.getValue(), kClassEntry.getKey());
            data.put(kClassEntry.getKey(),o);
        }
        return data;
    }

//    @Override
//    public <R> R classify(ResultSet resultSet, Map<String, Class<?>> map) throws Exception {
//        for (Map.Entry<String, Class<?>> kClassEntry : map.entrySet()) {
//            System.out.println(kClassEntry.getKey());
//            Object o = iResultDataTypeBaseStrategy.classify(resultSet, kClassEntry.getValue(), kClassEntry.getKey());
//            if(o !=null){
//                System.out.println(o.getClass());
//            }
//            System.out.println(o);
//        }
//        return null;
//    }
}
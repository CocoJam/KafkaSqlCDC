package DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.String;

import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base.IResultDataTypeBaseStrategy;
import DataBaseDataType.DataBaseDataGetter.ResultClassificationManager.Base.StringResultClassificationManagerBase;

import java.util.HashMap;
import java.util.Map;

public class StringResultClassificationStringManager extends StringResultClassificationManagerBase<String,HashMap<String,Object>> {
    protected IResultDataTypeBaseStrategy iResultDataTypeBaseStrategy;

    public StringResultClassificationStringManager(IResultDataTypeBaseStrategy iResultDataTypeBaseStrategy) {
        this.iResultDataTypeBaseStrategy = iResultDataTypeBaseStrategy;
    }

    @Override
    public HashMap<String,Object> classify(Map<String, Object> object, Map<String, Class<?>> map) throws Exception {
        HashMap<String,Object> data = new HashMap<>(map.size());
        for (Map.Entry<String, Class<?>> kClassEntry : map.entrySet()) {
            if(!object.containsKey(kClassEntry.getKey())){
                continue;
            }
            Object o = iResultDataTypeBaseStrategy.classify(object.get(kClassEntry.getKey()), kClassEntry.getValue(), kClassEntry.getKey());
            data.put(kClassEntry.getKey(),o);
        }
        return data;
    }
}

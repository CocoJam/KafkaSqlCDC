package DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultDataTypeBaseStrategy<O>{
    public <T> T classify(O o, Class<T> clazz, int location) throws Exception;
    public <T> T classify(O o,  Class<T> clazz, String location) throws Exception;
}

package DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultDataTypeBaseStrategy implements IResultDataTypeBaseStrategy<ResultSet> {
    @Override
    public abstract <T> T classify(ResultSet o, Class<T> clazz, int location) throws SQLException;
    @Override
    public abstract <T> T classify(ResultSet o, Class<T> clazz, String location) throws SQLException;
}
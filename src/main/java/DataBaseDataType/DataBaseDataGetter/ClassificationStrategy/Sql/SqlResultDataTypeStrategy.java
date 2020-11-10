package DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Sql;

import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base.ResultDataTypeBaseStrategy;

import java.math.BigDecimal;
import java.sql.*;

public class SqlResultDataTypeStrategy extends ResultDataTypeBaseStrategy {

    @Override
    public <T> T classify(ResultSet o, Class<T> clazz, int location) throws SQLException {
        if (String.class.equals(clazz)) {
            return (T) o.getString(location);
        } else if (Long.class.equals(clazz)) {
            Long l = o.getLong(location);
            return (T) l;
        } else if (Boolean.class.equals(clazz)) {
            Boolean b = o.getBoolean(location);
            return (T) b;
        } else if (Clob.class.equals(clazz)) {
            return (T) o.getClob(location);
        } else if (NClob.class.equals(clazz)) {
            return (T) o.getNClob(location);
        } else if (Date.class.equals(clazz)) {
            return (T) o.getDate(location);
        } else if (Double.class.equals(clazz)) {
            Double d = o.getDouble(location);
            return (T) d;
        } else if (Integer.class.equals(clazz)) {
            Integer i = o.getInt(location);
            return (T) i;
        } else if (Float.class.equals(clazz)) {
            Float f = o.getFloat(location);
            return (T) f;
        } else if (Short.class.equals(clazz)) {
            Short s = o.getShort(location);
            return (T) s;
        } else if (Time.class.equals(clazz)) {
            return (T) o.getTime(location);
        } else if (Timestamp.class.equals(clazz)) {
            return (T) o.getTimestamp(location);
        } else if (BigDecimal.class.equals(clazz)) {
            return (T) o.getBigDecimal(location);
        } else if (Byte[].class.equals(clazz)) {
            return (T) o.getBytes(location);
        }
        return (T) o.getObject(location);
    }

    @Override
    public <T> T classify(ResultSet o, Class<T> clazz, String location) throws SQLException {
        if (String.class.equals(clazz)) {
            return (T) o.getString(location);
        } else if (Long.class.equals(clazz)) {
            Long l = o.getLong(location);
            return (T) l;
        } else if (Boolean.class.equals(clazz)) {
            Boolean b = o.getBoolean(location);
            return (T) b;
        } else if (Clob.class.equals(clazz)) {
            return (T) o.getClob(location);
        } else if (NClob.class.equals(clazz)) {
            return (T) o.getNClob(location);
        } else if (Date.class.equals(clazz)) {
            return (T) o.getDate(location);
        } else if (Double.class.equals(clazz)) {
            Double d = o.getDouble(location);
            return (T) d;
        } else if (Integer.class.equals(clazz)) {
            Integer i = o.getInt(location);
            return (T) i;
        } else if (Float.class.equals(clazz)) {
            Float f = o.getFloat(location);
            return (T) f;
        } else if (Short.class.equals(clazz)) {
            Short s = o.getShort(location);
            return (T) s;
        } else if (Time.class.equals(clazz)) {
            return (T) o.getTime(location);
        } else if (Timestamp.class.equals(clazz)) {
            return (T) o.getTimestamp(location);
        } else if (BigDecimal.class.equals(clazz)) {
            return (T) o.getBigDecimal(location);
        } else if (Byte[].class.equals(clazz)) {
            return (T) o.getBytes(location);
        }
        return (T) o.getObject(location);
    }
}


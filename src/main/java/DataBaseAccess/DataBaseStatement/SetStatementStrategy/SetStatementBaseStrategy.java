package DataBaseAccess.DataBaseStatement.SetStatementStrategy;

import DataBaseAccess.DataBaseStatement.SetStatementStrategy.Base.ISetStatementStrategy;

import java.math.BigDecimal;
import java.sql.*;

public class SetStatementBaseStrategy<O> implements ISetStatementStrategy<PreparedStatement,O> {

    @Override
    public PreparedStatement setParameter(PreparedStatement statement, int location, Class<?> oClazz, O o) throws SQLException {
//        System.out.println(statement.getParameterMetaData().getParameterCount());
        if (String.class.equals(oClazz)) {
            statement.setString(location, (String) o);
            return statement;
        } else if (Long.class.equals(oClazz)) {
            statement.setLong(location, (Long) o);
            return statement;
        } else if (Boolean.class.equals(oClazz)) {
            statement.setBoolean(location, (Boolean) o);
            return statement;
        } else if (Clob.class.equals(oClazz)) {
            statement.setClob(location, (Clob) o);
            return statement;
        } else if (NClob.class.equals(oClazz)) {
            statement.setNClob(location, (NClob) o);
            return statement;
        } else if (Date.class.equals(oClazz)) {
            statement.setDate(location, (Date) o);
            return statement;
        } else if (Double.class.equals(oClazz)) {
            statement.setDouble(location, (Double) o);
            return statement;
        } else if (Integer.class.equals(oClazz)) {
            statement.setInt(location, (Integer) o);
            return statement;
        }else if (Float.class.equals(oClazz)) {
            statement.setFloat(location, (Float) o);
            return statement;
        }else if (Short.class.equals(oClazz)) {
            statement.setShort(location, (Short) o);
            return statement;
        }else if (Time.class.equals(oClazz)) {
            statement.setTime(location, (Time) o);
            return statement;
        }else if (Timestamp.class.equals(oClazz)) {
            statement.setTimestamp(location, (Timestamp) o);
            return statement;
        }else if (BigDecimal.class.equals(oClazz)) {
            statement.setBigDecimal(location, (BigDecimal) o);
            return statement;
        }else if (byte[].class.equals(oClazz)) {
            statement.setBytes(location, (byte[]) o);
            return statement;
        }
        throw new IllegalStateException("Unexpected value: " + oClazz);
    }

    @Override
    public PreparedStatement setParameter(PreparedStatement statement, int location, O o) throws SQLException {
        Class<?> oClazz = o.getClass();
        return setParameter(statement, location, oClazz, o);
    }
}

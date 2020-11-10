package DataBaseDataType.DataBaseDataClassification.ClassificationStrategy.MsSql;

import DataBaseDataType.DataBaseDataClassification.ClassificationStrategy.Base.DataTypeBaseStrategy;

import java.math.BigDecimal;
import java.sql.*;

public class MssqlDataTypeStrategy extends DataTypeBaseStrategy<Integer,Class<?>> {
    @Override
    public Class<?> classify(Integer typeNumber) {
        switch (typeNumber){
            case 1:
            case -16:
            case -1:
            case -15:
            case -9:
            case 12:
            case -145:
                return String.class;
            case -5:
                return Long.class;
            case -7:
            case 16:
                return Boolean.class;
            case 2005:
                return Clob.class;
            case 2011:
                return NClob.class;
            case 91:
                return Date.class;
            case 8:
            case 6:
                return Double.class;
            case 4:
                return Integer.class;
            case 7:
                return Float.class;
            case 5:
            case -6:
                return Short.class;
            case 92:
                return Time.class;
            case 93:
            case -151:
            case -150:
                return Timestamp.class;
            case 3:
            case 2:
                return BigDecimal.class;
            case -2:
            case -4:
                return Byte[].class;
            default:
                return Object.class;
        }
    }
}


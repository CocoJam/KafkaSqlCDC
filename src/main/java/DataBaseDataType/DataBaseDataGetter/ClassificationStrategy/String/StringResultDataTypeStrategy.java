package DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.String;

import DataBaseDataType.DataBaseDataGetter.ClassificationStrategy.Base.StringResultDataTypeBaseStrategy;

import java.math.BigDecimal;
import java.sql.*;

public class StringResultDataTypeStrategy extends StringResultDataTypeBaseStrategy {
    @Override
    public <T> T classify(Object oo, Class<T> clazz, int location) throws Exception {
        if(oo.getClass().equals(clazz)){
            System.out.println("equals");
            return (T) oo;
        }
        String o = oo.toString();
        if (String.class.equals(clazz)) {
            return (T) o;
        } else if (Long.class.equals(clazz)) {
            Long l = Long.parseLong(o);
            return (T) l;
        } else if (Boolean.class.equals(clazz)) {
            Boolean b = Boolean.parseBoolean(o);
            return (T) b;
        }
//        else if (Clob.class.equals(clazz)) {
//            return (T) o.getClob(location);
//        } else if (NClob.class.equals(clazz)) {
//            return (T) o.getNClob(location);
//        }
        else if (Date.class.equals(clazz)) {
            return (T) Date.valueOf(o);
        } else if (Double.class.equals(clazz)) {
            Double d = Double.parseDouble(o);
            return (T) d;
        } else if (Integer.class.equals(clazz)) {
            Integer i = Integer.parseInt(o);
            return (T) i;
        } else if (Float.class.equals(clazz)) {
            Float f = Float.parseFloat(o);
            return (T) f;
        } else if (Short.class.equals(clazz)) {
            Short s = Short.parseShort(o);
            return (T) s;
        } else if (Time.class.equals(clazz)) {
            return (T) Time.valueOf(o);
        } else if (Timestamp.class.equals(clazz)) {
            return (T) Timestamp.valueOf(o);
        } else if (BigDecimal.class.equals(clazz)) {
            long l = Long.parseLong(o);
            return (T) BigDecimal.valueOf(l);
        } else if (Byte[].class.equals(clazz)) {
            return (T) Byte.valueOf(o);
        }
        return (T) o;
    }

//    @Override
//    public <T> T classify(Object o, Class<T> clazz, String location) throws Exception {
//        return null;
//    }

    @Override
    public <T> T classify(Object oo, Class<T> clazz, String location) throws Exception {
        if(oo.getClass().equals(clazz)){
            System.out.println("equals");
            return (T) oo;
        }
        String o = oo.toString();
        if (String.class.equals(clazz)) {
            return (T) o;
        } else if (Long.class.equals(clazz)) {
            Long l = Long.parseLong(o);
            return (T) l;
        } else if (Boolean.class.equals(clazz)) {
            Boolean b = Boolean.parseBoolean(o);
            return (T) b;
        }
//        else if (Clob.class.equals(clazz)) {
//            return (T) o.getClob(location);
//        } else if (NClob.class.equals(clazz)) {
//            return (T) o.getNClob(location);
//        }
        else if (Date.class.equals(clazz)) {
            return (T) Date.valueOf(o);
        } else if (Double.class.equals(clazz)) {
            Double d = Double.parseDouble(o);
            return (T) d;
        } else if (Integer.class.equals(clazz)) {
            Integer i = Integer.parseInt(o);
            return (T) i;
        } else if (Float.class.equals(clazz)) {
            Float f = Float.parseFloat(o);
            return (T) f;
        } else if (Short.class.equals(clazz)) {
            Short s = Short.parseShort(o);
            return (T) s;
        } else if (Time.class.equals(clazz)) {
            return (T) Time.valueOf(o);
        } else if (Timestamp.class.equals(clazz)) {
            return (T) Timestamp.valueOf(o);
        } else if (BigDecimal.class.equals(clazz)) {
            double l = Double.parseDouble(o);
            return (T) BigDecimal.valueOf(l);
        } else if (Byte[].class.equals(clazz)) {
            byte[] b = o.getBytes();
            return (T) b;
        }
        return (T) o;
    }
}

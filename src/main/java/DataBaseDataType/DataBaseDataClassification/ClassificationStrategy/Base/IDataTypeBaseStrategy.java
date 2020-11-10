package DataBaseDataType.DataBaseDataClassification.ClassificationStrategy.Base;

public interface IDataTypeBaseStrategy <I,O>{
//    public <T> T cast(Object o, Class<T> clazz);
    public O classify(I typeNumber);
}

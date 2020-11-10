package DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Base;

import DataBaseDataType.DataBaseDataClassification.ClassificationStrategy.Base.IDataTypeBaseStrategy;

public abstract class ClassificationManagerBase implements IClassificationManager, ISchemaClassificationManager{
    protected IDataTypeBaseStrategy<Integer,Class<?>> dataTypeBaseStrategy;

    public ClassificationManagerBase(IDataTypeBaseStrategy<Integer,Class<?>>  dataTypeBaseStrategy) {
        this.dataTypeBaseStrategy = dataTypeBaseStrategy;
    }
}

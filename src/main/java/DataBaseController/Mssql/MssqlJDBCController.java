package DataBaseController.Mssql;

import Config.JDBC.InitiateSchema.TableSchema;
import Config.JDBC.JDBCConnection.Connection;
import DataBaseAccess.DataBaseAccessManager.SQLDataBaseAccessBaseManager;
import DataBaseAccess.DataBaseManager.Sql.SQLManagerStrategy;
import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query.PreparedSQLUpdate;
import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query.SQLQuery;
import DataBaseController.Base.JDBCBaseController;
import DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Mssql.MssqlClassificationManager;
import DataQuery.OperationLoader.QueryLoaderBuilder.DeleteQueryLoaderBuilder;
import DataQuery.OperationLoader.QueryLoaderBuilder.InsertQueryLoaderBuilder;
import DataQuery.OperationLoader.QueryLoaderBuilder.UpdateQueryLoaderBuilder;
import DataQuery.OperationLoader.QueryLoaderBuilder.WhereFilterEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MssqlJDBCController extends JDBCBaseController {
    static Logger logger = LoggerFactory.getLogger(MssqlJDBCController.class);

    public MssqlJDBCController(Connection connConfig, TableSchema tableSchema) throws ClassNotFoundException {
        super(connConfig, tableSchema);
        String connectionStr = String.format("%s://%s:%s;databaseName=%s;user=%s;password=%s",
                connConfig.getConnectionType(), connConfig.getAddress().getiP(), connConfig.getAddress().getPort(),
                connConfig.getDataBase(), connConfig.getUser().getUsername(), connConfig.getUser().getPassword()
        );
        logger.info( String.format("mssql connection str: %s", connectionStr));
        sqlDataBaseAccessBaseManager = new SQLDataBaseAccessBaseManager("com.microsoft.sqlserver.jdbc.SQLServerDriver",
                connectionStr);
        initQuery = tableSchema.getTableInitQuery();
        sqlManagerStrategy = new SQLManagerStrategy(sqlDataBaseAccessBaseManager);
        classificationManagerBase = new MssqlClassificationManager();
        HashMap<String, Integer> dataHash = schemaQuery();
        CLASS_MAP = classificationManagerBase.classify(dataHash);
    }
}

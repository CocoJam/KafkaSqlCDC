package DataBaseController.Base;

import Config.JDBC.InitiateSchema.TableSchema;
import Config.JDBC.JDBCConnection.Connection;
import DataBaseAccess.DataBaseAccessManager.IDataBaseAccessManager;
import DataBaseAccess.DataBaseAccessManager.SQLDataBaseAccessBaseManager;
import DataBaseAccess.DataBaseManager.Sql.SQLManagerStrategy;
import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query.PreparedSQLUpdate;
import DataBaseAccess.DataBaseStatement.SQLDataBaseStatement.Query.SQLQuery;
import DataBaseController.Mssql.MssqlJDBCController;
import DataBaseDataType.DataBaseDataClassification.ClassifictionManager.Base.ClassificationManagerBase;
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

public abstract class JDBCBaseController {
    protected Config.JDBC.JDBCConnection.Connection connConfig;
    protected ClassificationManagerBase classificationManagerBase;
    protected SQLDataBaseAccessBaseManager sqlDataBaseAccessBaseManager;
    protected SQLManagerStrategy sqlManagerStrategy;
    protected String initQuery;
    protected TableSchema tableSchema;
    protected Map<String,Class<?>> CLASS_MAP;
    static Logger logger = LoggerFactory.getLogger(MssqlJDBCController.class);


    public JDBCBaseController(Connection connConfig, TableSchema tableSchema) {
        this.connConfig = connConfig;
        this.tableSchema = tableSchema;
    }

    public HashMap<String, Integer> schemaQuery() {
        HashMap<String, Integer> dataHash = new HashMap<>();
        try (java.sql.Connection connection = sqlManagerStrategy.access();) {
            SQLQuery query = new SQLQuery(connection, initQuery);
            ResultSet resultSet = query.execute();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                dataHash.put(metaData.getColumnName(i), metaData.getColumnType(i));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return dataHash;
    }

    public void Insert(Map<String,Object> jsonObject) throws Exception {
        InsertQueryLoaderBuilder insertQueryLoaderBuilder = new InsertQueryLoaderBuilder(
                tableSchema.getTableName(),jsonObject,CLASS_MAP);
        String insertQueryStr = insertQueryLoaderBuilder.compile().compile();
        try(java.sql.Connection connection = sqlManagerStrategy.access(); ){
            PreparedSQLUpdate query = new PreparedSQLUpdate(connection, insertQueryStr);
            insertQueryLoaderBuilder.setObjectMap(query);
            query.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
            throw throwables;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void update(Map<String,Object> jsonObject) throws Exception {
        update(jsonObject,jsonObject);
    }

    public void update(Map<String,Object> jsonObject,Map<String,Object> updateObject) throws Exception {
        UpdateQueryLoaderBuilder updateQueryLoaderBuilder = new UpdateQueryLoaderBuilder(tableSchema.getTableName(),jsonObject,CLASS_MAP,updateObject);
        WhereFilterEnum escapeOrOnly =  WhereFilterEnum.valueOf(tableSchema.getUpdateEscapes().getDeleteEscapeOrOnly().toUpperCase());
        List<String> escapes = tableSchema.getUpdateEscapes().getWhereClauseEscape();
        updateQueryLoaderBuilder.whereEscapes(escapes, escapeOrOnly);
        String updateQueryStr = updateQueryLoaderBuilder.compile().compile();
        try(java.sql.Connection connection = sqlManagerStrategy.access(); ){
            PreparedSQLUpdate query = new PreparedSQLUpdate(connection, updateQueryStr);
            updateQueryLoaderBuilder.setObjectMap(query);
            query.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
            throw throwables;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(Map<String,Object> jsonObject) throws Exception {
        DeleteQueryLoaderBuilder deleteQueryLoader = new DeleteQueryLoaderBuilder(
                tableSchema.getTableName(),jsonObject,CLASS_MAP);
        WhereFilterEnum escapeOrOnly =  WhereFilterEnum.valueOf(tableSchema.getDeleteEscapes().getDeleteEscapeOrOnly().toUpperCase());
        List<String> escapes = tableSchema.getDeleteEscapes().getWhereClauseEscape();
        deleteQueryLoader.whereEscapes(escapes, escapeOrOnly);
        String deleteQueryStr = deleteQueryLoader.compile().compile();
        try(java.sql.Connection connection = sqlManagerStrategy.access(); ){
            PreparedSQLUpdate query = new PreparedSQLUpdate(connection, deleteQueryStr);
            deleteQueryLoader.setObjectMap(query);
            query.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
            throw throwables;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw e;
        };
    }
}

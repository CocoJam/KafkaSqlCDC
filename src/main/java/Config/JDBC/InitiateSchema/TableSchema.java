package Config.JDBC.InitiateSchema;

import DataBaseController.Base.JDBCAction;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TableSchema {
    private String tableName;
    private String tableInitQuery;
    private Escapes updateEscapes;
    private Escapes deleteEscapes;
    private Map<String, JDBCAction> actionMapping;

    public Map<String, JDBCAction> getActionMapping() {
        return actionMapping;
    }

    public void setActionMapping(Map<String, JDBCAction> actionMapping) {
        this.actionMapping = actionMapping;
    }

    public Escapes getUpdateEscapes() {
        return updateEscapes;
    }

    public void setUpdateEscapes(Escapes updateEscapes) {
        this.updateEscapes = updateEscapes;
    }

    public Escapes getDeleteEscapes() {
        return deleteEscapes;
    }

    public void setDeleteEscapes(Escapes deleteEscapes) {
        this.deleteEscapes = deleteEscapes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableInitQuery() {
        return tableInitQuery;
    }

    public void setTableInitQuery(String tableInitQuery) {
        this.tableInitQuery = tableInitQuery;
    }

    public static void main(String[] args) throws IOException {
        TableSchema tableSchema = new TableSchema();
        tableSchema.setTableInitQuery("select top 1 * from dbo.EOSS_BOM_CDC");
        tableSchema.setTableName("dbo.EOSS_BOM_CDC");
        Escapes delete = new Escapes();

        delete.setWhereClauseEscape(Collections.singletonList("JOB_REF_NO"));
        delete.setDeleteEscapeOrOnly("ONLY");

        Escapes update = new Escapes();
        update.setWhereClauseEscape(Collections.singletonList("JOB_REF_NO"));
        update.setDeleteEscapeOrOnly("ONLY");

        tableSchema.setDeleteEscapes(delete);
        tableSchema.setUpdateEscapes(update);

        Map<String,JDBCAction> action = new HashMap<>();
        action.put("INSERT",JDBCAction.INSERT);
        action.put("UPDATE",JDBCAction.UPDATE);
        action.put("DELETE",JDBCAction.DELETE);

        tableSchema.setActionMapping(action);

        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);
        FileWriter writer = new FileWriter("./table.yaml");
        yaml.dump(tableSchema, writer);
    }
}

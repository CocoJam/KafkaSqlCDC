package Config.JDBC.InitiateSchema;

import java.util.List;

public class Escapes {
    private List<String> WhereClauseEscape;
    private String DeleteEscapeOrOnly;

    public List<String> getWhereClauseEscape() {
        return WhereClauseEscape;
    }

    public void setWhereClauseEscape(List<String> whereClauseEscape) {
        WhereClauseEscape = whereClauseEscape;
    }

    public String getDeleteEscapeOrOnly() {
        return DeleteEscapeOrOnly;
    }

    public void setDeleteEscapeOrOnly(String deleteEscapeOrOnly) {
        DeleteEscapeOrOnly = deleteEscapeOrOnly;
    }
}

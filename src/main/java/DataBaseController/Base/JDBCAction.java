package DataBaseController.Base;

public enum JDBCAction {
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    public final String label;
    private JDBCAction(String label) {
        this.label = label;
    }
}

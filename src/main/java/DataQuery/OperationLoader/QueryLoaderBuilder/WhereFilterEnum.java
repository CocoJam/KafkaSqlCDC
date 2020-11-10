package DataQuery.OperationLoader.QueryLoaderBuilder;

public enum WhereFilterEnum {
    ESCAPE("ESCAPE"),
    ONLY("ONLY"),
    None("NONE");

    public final String label;
    private WhereFilterEnum(String label) {
        this.label = label;
    }
}

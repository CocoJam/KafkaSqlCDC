package DataQuery.DataNode.WhereNode;

public class WhereDataNodeCompose {
    enum OP {
        AND {
            @Override
            public String toString() {
                return "AND";
            }
        },
        OR {
            @Override
            public String toString() {
                return "OR";
            }
        }
    }

    public static <T extends WhereDataNode> T SINGLE(String t1) {
        return (T) new WhereDataNode(t1);
    }

    public static <T extends WhereDataNode> T AND(String t1, T t2) {
        return append((T) new WhereDataNode(t1), t2, OP.AND);
    }

    public static <T extends WhereDataNode> T AND(T t1, String t2) {
        return append(t1, (T) new WhereDataNode(t2), OP.AND);
    }

    public static <T extends WhereDataNode> T OR(String t1, T t2) {
        return append((T) new WhereDataNode(t1), t2, OP.OR);
    }

    public static <T extends WhereDataNode> T OR(T t1, String t2) {
        return append(t1, (T) new WhereDataNode(t2), OP.OR);
    }

    public static <T extends WhereDataNode> T AND(String t1, String t2) {
        return append((T) new WhereDataNode(t1), (T) new WhereDataNode(t2), OP.AND);
    }

    public static <T extends WhereDataNode> T OR(String t1, String t2) {
        return append((T) new WhereDataNode(t1), (T) new WhereDataNode(t2), OP.OR);
    }


    public static <T extends WhereDataNode> T AND(T t1, T t2) {
        return append(t1, t2, OP.AND);
    }

    public static <T extends WhereDataNode> T OR(T t1, T t2) {
        return append(t1, t2, OP.OR);
    }

    private static <T extends WhereDataNode> T append(T t1, T t2, OP op) {
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        }
        WhereDataNode orNode = new WhereDataNode(op.toString());
        orNode.append(t1);
        orNode.append(t2);
        return (T) orNode;
    }
}

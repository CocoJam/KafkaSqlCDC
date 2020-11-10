package DataQuery.DataNode.Base;

public enum Action {
    SELECT{
        @Override
        public String toString() {
            return "SELECT";
        }
    },
    INSERT{
        @Override
        public String toString() {
            return "INSERT INTO";
        }
    },
    DELETE{
        @Override
        public String toString() {
            return "DELETE";
        }
    },
    UPDATE{
        @Override
        public String toString() {
            return "UPDATE";
        }
    },
    WHERE{
        @Override
        public String toString() {
            return " WHERE ";
        }
    },
    SET{
        @Override
        public String toString() {
            return " SET ";
        }
    }
}

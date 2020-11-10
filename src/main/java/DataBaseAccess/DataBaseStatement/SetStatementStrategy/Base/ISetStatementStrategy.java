package DataBaseAccess.DataBaseStatement.SetStatementStrategy.Base;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface ISetStatementStrategy <R,O>{
    public R setParameter(R statement, int location ,Class<?> clazz, O o) throws SQLException;
    public R setParameter(R statement, int location , O o) throws SQLException;
}

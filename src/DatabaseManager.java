import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class DatabaseManager implements AutoCloseable{
    public Connection _dbConnection;
    public abstract void connect() throws ClassNotFoundException, SQLException;
    public abstract void ExecuteWithoutResult(String sqlQuery) throws SQLException, ClassNotFoundException;
    public abstract List<Map<String, Object>> ExecuteWithResult(String sqlQuery) throws SQLException, ClassNotFoundException;
}

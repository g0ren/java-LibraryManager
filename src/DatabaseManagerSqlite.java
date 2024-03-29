import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManagerSqlite extends DatabaseManager{
    public List<Map<String, Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }
    public DatabaseManagerSqlite() throws ClassNotFoundException, SQLException {
        this.connect();
    }

    public DatabaseManagerSqlite(DatabaseManagerSqlite databaseManagerSqlite) throws ClassNotFoundException, SQLException {

        this.connect();
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        _dbConnection = DriverManager.getConnection("jdbc:sqlite:library.sqlite");
    }

    @Override
    public void ExecuteWithoutResult(String sqlQuery) throws SQLException, ClassNotFoundException {
        this.connect();
        try (var statement = _dbConnection.createStatement()) {
            statement.execute(sqlQuery);
        }
    }

    @Override
    public List ExecuteWithResult(String sqlQuery) throws SQLException, ClassNotFoundException {
        this.connect();
        try (
                var statement = _dbConnection.createStatement();
                ResultSet rs = statement.executeQuery(sqlQuery);
                )
        {
            return resultSetToArrayList(rs);
        }
    }

    @Override
    public void close() throws Exception {
        _dbConnection.close();
    }
}

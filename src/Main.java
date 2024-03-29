import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserInterface userInterface = new ConsoleUserInterface(new LibraryManagerSqlite());
        userInterface.RunInterface();
    }
}
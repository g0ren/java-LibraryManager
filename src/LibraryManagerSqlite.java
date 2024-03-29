import javax.lang.model.element.Name;
import java.sql.SQLException;
import java.util.*;

public class LibraryManagerSqlite extends LibraryManager {

    public LibraryManagerSqlite() throws SQLException, ClassNotFoundException {
        this._db = new DatabaseManagerSqlite();
        this.CreateDB();
    }
    @Override
    protected void CreateDB()
    {
        try (var db = this._db){
            db.ExecuteWithoutResult("CREATE TABLE IF NOT EXISTS Books (" +
                    "ID integer PRIMARY KEY," +
                    "Author text," +
                    "Name text," +
                    "Editor text," +
                    "Publisher text," +
                    "Place text," +
                    "Year text" +
                    ");");
        } catch (SQLException e) {
            System.out.println(STR."SQL Exception while creating DB: \{e.getMessage()}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void AddBook(Book book){
        try(var db = this._db){
            db.ExecuteWithoutResult("INSERT INTO Books " +
                    "(Author, Name, Editor, Publisher, Place, Year) " +
                    "VALUES " +
                    "('" +
                    book.author + "', '" +
                    book.name + "', '" +
                    book.editor + "', '" +
                    book.publisher + "', '" +
                    book.place + "', '" +
                    book.year +
                    "')");
        }catch (SQLException e) {
            System.out.println(STR."SQL Exception while inserting into DB: \{e.getMessage()}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void AddBook(String author, String name, String editor,
                        String publisher, String place, String year){
        Book book = new Book(author, name, editor, publisher, place, year);
        AddBook(book);
    }

    @Override
    public Map<Integer,Book> SearchBookByName(String name){
        HashMap<Integer,Book> results = new HashMap<>();
        try (var db = this._db){
            var rs = db.ExecuteWithResult(STR."SELECT * FROM Books WHERE Name LIKE '%\{name}%';");
            for(var line : rs){
                results.put((Integer) line.get("ID"),
                        new Book(line.get("Author").toString(),
                                line.get("Name").toString(),
                                line.get("Editor").toString(),
                                line.get("Publisher").toString(),
                                line.get("Place").toString(),
                                line.get("Year").toString()
                        ));
            }
        }
        catch (SQLException e) {
            System.out.println(STR."SQL Exception while searching DB: \{e.getMessage()}");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public Map<Integer,Book> SearchBookByAuthor(String author) {
        HashMap<Integer,Book> results = new HashMap<>();
        try (var db = this._db){
            var rs = db.ExecuteWithResult(STR."SELECT * FROM Books WHERE Author LIKE '%\{author}%';");
            for(var line : rs){
                results.put((Integer) line.get("ID"),
                        new Book(line.get("Author").toString(),
                                line.get("Name").toString(),
                                line.get("Editor").toString(),
                                line.get("Publisher").toString(),
                                line.get("Place").toString(),
                                line.get("Year").toString()
                        ));
            }
        }
        catch (SQLException e) {
            System.out.println(STR."SQL Exception while searching DB: \{e.getMessage()}");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public Map<Integer,Book> ListAllBooks(){
        Map<Integer,Book> results = new HashMap<>();
        try (var db = this._db){
            var rs = db.ExecuteWithResult("SELECT * " +
                    "FROM Books;");
            for(var line : rs){
                results.put((Integer) line.get("ID"),
                        new Book(line.get("Author").toString(),
                                line.get("Name").toString(),
                                line.get("Editor").toString(),
                                line.get("Publisher").toString(),
                                line.get("Place").toString(),
                                line.get("Year").toString()
                        ));
            }
        }
        catch (SQLException e) {
            System.out.println(STR."SQL Exception while getting contents of the DB: \{e.getMessage()}");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public void EmptyDB(){
        try(var db = this._db){
            db.ExecuteWithoutResult("DROP TABLE IF EXISTS Books;");
            db.ExecuteWithoutResult("CREATE TABLE IF NOT EXISTS Books (" +
                    "ID integer PRIMARY KEY," +
                    "Author text," +
                    "Name text," +
                    "Editor text," +
                    "Publisher text," +
                    "Place text," +
                    "Year text" +
                    ");");
        }
        catch (SQLException e) {
            System.out.println(STR."SQL Exception while clearing DB: \{e.getMessage()}");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

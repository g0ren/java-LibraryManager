import java.util.Map;

public abstract class LibraryManager{
    public DatabaseManager _db;


    protected abstract void CreateDB();
    public abstract void AddBook(String author, String name, String editor,
                        String publisher, String place, String year);
    public abstract void AddBook(Book book);
    public abstract Map<Integer, Book> ListAllBooks();
    public abstract Map<Integer, Book> SearchBookByName(String name);
    public abstract Map<Integer, Book> SearchBookByAuthor(String author);
    public abstract void EmptyDB();
}

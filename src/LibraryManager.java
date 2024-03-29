import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class LibraryManager{
    public DatabaseManager _db;

    /*protected String FormatBookString(String author, String name, String editor,
                                    String publisher, String place, String year){
        return author +
                ((!Objects.equals(author, ""))? " " : "") +
                name +
                ((!Objects.equals(editor, ""))? " / " : "") +
                editor +
                ". " +
                place +
                ((!Objects.equals(place, "") && !Objects.equals(publisher, ""))? ": " : "") +
                publisher +
                ((((!Objects.equals(place, "") || !Objects.equals(publisher, "")))
                        && (!Objects.equals(year, "")))? ", " : "") +
                year +
                ((!Objects.equals(year, ""))? "." : "");
    }*/

    protected abstract void CreateDB();
    public abstract void AddBook(String author, String name, String editor,
                        String publisher, String place, String year);
    public abstract void AddBook(Book book);
    public abstract Map<Integer, Book> ListAllBooks();
    public abstract Map<Integer, Book> SearchBookByName(String name);
    public abstract Map<Integer, Book> SearchBookByAuthor(String author);
    public abstract void EmptyDB();
}

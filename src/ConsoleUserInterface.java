import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleUserInterface extends UserInterface{
    public ConsoleUserInterface(LibraryManager libraryManager){
        this._lm = libraryManager;
    }

    @Override
    public void RunInterface() {
        Scanner in = new Scanner(System.in);
        String command;
        while (true){
            System.out.println("Welcome to Library Manager CLI!\n" +
                    "Enter your command (help for help)");
            command = in.nextLine().trim();
            if(Objects.equals(command,"exit")||Objects.equals(command,"quit")){
                return;
            }
            else {
                ParseCommand(command);
            }
        }
    }

    private void Help(){
        System.out.println("Currently available commands:");
        System.out.println("add - call the interface for adding books");
        System.out.println("search - call the search interface");
        System.out.println("list - list all books currently in database\n" +
                "(may overload console for large databases)");
        System.out.println("clear - remove all books from database\n" +
                "WARNING: this will irreversibly remove everything from the database");
        System.out.println("help - print this help");
        System.out.println("quit or exit - exit the program");
    }

    private void Clear(){
        Scanner in = new Scanner(System.in);
        System.out.println("Are you sure that you want to permanently remove ALL entries from the book database?");
        System.out.println("Enter YES in capital letters if you wish to do this, or anything else to cancel");
        String yes = in.nextLine().trim();
        if (Objects.equals(yes, "YES")){
            this._lm.EmptyDB();
            System.out.println("All entries have been removed from the database");
        }
    }

    private void PrintOutMap(Map<Integer, Book> map){
        for(var i : map.keySet()){
            System.out.println(STR."ID: \{i}. \{map.get(i)}");
        }
    }

    private void ListAll(){
        System.out.println("All books in the database with their IDs:");
        PrintOutMap(this._lm.ListAllBooks());
    }

    private void Search(){
        Map<Integer,Book> results = new HashMap<>();
        boolean author = false;
        String key = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 'author' to search by author or anything else to search by book name");
        if(Objects.equals(in.nextLine().trim(), "author")){
            author = true;
        }
        System.out.println(STR."Enter the \{(author ? "author name" : "book name")} to search by");
        key = in.nextLine();
        if (author){
            results = this._lm.SearchBookByAuthor(key);
        }
        else {
            results = this._lm.SearchBookByName(key);
        }
        PrintOutMap(results);
    }

    public void Add(){
        String author = "";
        String name = "";
        String editor = "";
        String publisher = "";
        String place = "";
        String year ="";
        Scanner in = new Scanner(System.in);
        System.out.println("Adding book to DB");
        System.out.println("Enter author's name (or enter nothing to skip)");
        author = in.nextLine().trim();
        System.out.println("Enter book name");
        name = in.nextLine().trim();
        System.out.println("Enter editor's name (or enter nothing to skip)");
        editor = in.nextLine().trim();
        System.out.println("Enter publisher's name (or enter nothing to skip)");
        publisher = in.nextLine().trim();
        System.out.println("Enter place of publishing (or enter nothing to skip)");
        place = in.nextLine().trim();
        System.out.println("Enter year of publishing (or enter nothing to skip)");
        year = in.nextLine().trim();
        Book book = new Book(author, name, editor, publisher, place, year);
        System.out.println("Do you want to add the following book?\n" +
                STR."\{book}\n" +
                "y/N");
        String yes = in.nextLine().trim();
        if (yes.startsWith("y")||
                yes.startsWith("Y")){
            this._lm.AddBook(book);
            System.out.println("Book added successfully");
        }
    }

    private void ParseCommand(String command){
        switch (command){
            case "add":
                Add();
                break;
            case "search":
                Search();
                break;
            case "list":
                ListAll();
                break;
            case "clear":
                Clear();
                break;
            case "help":
                Help();
                break;
            default:
                System.out.println("Invalid command! Type 'help' for help");
        }
    }
}

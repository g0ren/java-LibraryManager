import java.util.Objects;

public class Book{
    public String author;
    public String name;
    public String editor;
    public String publisher;
    public String place;
    public String year;
    public Book(String author, String name, String editor, String publisher, String place, String year){
        this.author = author;
        this.name = name;
        this.editor = editor;
        this.publisher = publisher;
        this.place = place;
        this.year = year;
    }
    public String toString(){
        return this.author +
                ((!Objects.equals(this.author, ""))? " " : "") +
                this.name +
                ((!Objects.equals(this.editor, ""))? " / " : "") +
                this.editor +
                ". " +
                this.place +
                ((!Objects.equals(this.place, "") && !Objects.equals(this.publisher, ""))? ": " : "") +
                this.publisher +
                ((((!Objects.equals(this.place, "") || !Objects.equals(this.publisher, "")))
                        && (!Objects.equals(this.year, "")))? ", " : "") +
                this.year +
                ((!Objects.equals(this.year, ""))? "." : "");
    }
}

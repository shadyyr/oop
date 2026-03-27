public class hw4 {
    public static void main(String[] args){

    }
}

abstract class Book{
    private String author;
    private String title;
    private String isbn;

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    abstract public String getBookType();

}

class BookstoreBook extends Book{

}

class LibraryBook extends Book{

}

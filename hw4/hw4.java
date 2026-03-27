public class hw4 {
    public static void main(String[] args){

    }
}

abstract class Book{
    //variable initializations
    private String author;
    private String title;
    private String isbn;

    //setters and getters
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

    //constructors
    public Book(String author, String title, String isbn){
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public Book(){
        author = "xxx";
        title = "xxx";
        isbn = "xxx";
    }

    //toString override
    @Override
    public String toString(){
        return "[" + isbn + "-" + title + " by " + author;
    }

    abstract public String getBookType();

}

class BookstoreBook extends Book{
    //variable initializations
    private double price;
    private boolean onSale;
    private double saleRate;

    //setters and getters
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return onSale;
    }
    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public double getSaleRate() {
        return saleRate;
    }
    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    //constructors
    public BookstoreBook(String author, String title, String isbn, double price, boolean onSale, double salesRate){
        super(author, title, isbn);
        this.price = price;
        this.onSale = onSale;
        this.salesRate = salesRate;
    }

    public BookstoreBook(double price, boolean onSale, double salesRate){
        this.price = price;
        this.onSale = onSale;
        this.salesRate = salesRate;
    }

    //toString override
    @Override
    public String toString(){
        return super.toString() + ", $" + price + "listed for $" + (price*saleRate);
    }

}

class LibraryBook extends Book{
    //variable initializations
    private String Subject;
    private String callNumber;

    //setters and getters
    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getCallNumber() {
        return callNumber;
    }
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    //constructors
    public LibraryBook(String author, String title, String isbn, String Subject, String callNumber){
        super(author, title, isbn);
        this.Subject = Subject;
        this.callNumber = callNumber;
    }

    public LibraryBook(String Subject, String callNumber){
        this.Subject = Subject;
        this.callNumber = callNumber;
    }

    //toString override
    @Override
    public String toString(){
        return super.toString() + "-" + callNumber;
    }

}

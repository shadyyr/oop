import java.util.*;

public class hw4 {
    public static void main(String[] args){
        //variable initializations
        Book[] array = new Book[100];
        String input = "";
        Scanner myScan = new Scanner(System.in);
        boolean bookCreation = true;
        boolean valid = false;

        //welcome message
        System.out.println("\t\tWelcome to the book program!");

        //creating books
        do{
            System.out.print("Would you like to create a book object? (yes/no): ");
            //checks for valid input
            do{
                input = myScan.nextLine().toLowerCase();
                if(!input.equals("yes") && !input.equals("no")){
                    System.out.print("Oops! That’s not a valid entry. Please try again: ");
                    valid = false;
                }
                else{
                    valid = true;
                }
            }while(valid == false);

            //create a book object
            if(input.equals("yes")){
                //get author, title, and isbn from user, ensure no spaces at the end, ensure all entries are uppercase, set delimiter to /
                System.out.println("Enter the author, title, and the isbn of the book separated by /: ");
                scanner.useDelimiter("/");
                String author = myScan.nextLine().trim().toUpperCase();
                String title = myScan.nextLine().trim().toUpperCase();
                String isbn = myScan.nextLine().trim().toUpperCase();
                System.out.println("Got it!");
            }
            //display all books
            else{
                //counts all library books and bookstore books
                int LBcount = 0;
                int BBcount = 0;
                for (Book b : array){
                    if (b.getBookType().equals("Library Book")){
                        LBcount++;
                    }
                    if (b.getBookType().equals("Bookstore Book")){
                        BBcount++;
                    }
                }

                //general msg
                System.out.println("Sure!");
                System.out.println("Here are all the books you entered...");

                //display all library books
                System.out.println("Library Books (" + LBcount + ")");
                for(Book b: array){
                    if (b != null && b.getBookType().equals("Library Book")){
                        System.out.println("\t" + b);
                    }
                }
                System.out.println("----");

                //display all bookstore books
                System.out.println("Bookstore Books (" + BBcount + ")");
                for(Book b: array){
                    if (b != null && b.getBookType().equals("Bookstore Book")){
                        System.out.println("\t" + b);
                    }
                }
                System.out.println("----");

                //condition to exit Book Creation do-while look
                bookCreation = false;
            }
        }while(bookCreation == true);

        //searching books

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
        return "[" + isbn + "-" + title.toUpperCase() + " by " + author.toUpperCase();
    }

    //getBookType function
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
    public BookstoreBook(String author, String title, String isbn, double price, boolean onSale, double saleRate){
        super(author, title, isbn);
        this.price = price;
        this.onSale = onSale;
        this.saleRate = saleRate;
    }

    public BookstoreBook(double price, boolean onSale, double saleRate){
        this.price = price;
        this.onSale = onSale;
        this.saleRate = saleRate;
    }

    //additional functions
    private double calculateDiscountedPrice(){
        return (price - (price*saleRate));
    }

    //toString override
    @Override
    public String toString(){
        if(onSale)
            return super.toString() + ", $" + price + " listed for $" + calculateDiscountedPrice() + "]";
        else
            return super.toString() + ", $" + price + "]";
    }

    //getBookType function
    @Override
    public String getBookType(){
        return "Bookstore Book";
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
        return super.toString() + "-" + callNumber + "]";
    }

    //getBookType function
    @Override
    public String getBookType(){
        return "Library Book";
    }
}

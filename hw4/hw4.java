import java.util.*;

public class hw4 {
    public static void main(String[] args){
        //variable initializations
        Book[] array = new Book[100];
        String input = "";
        Scanner myScan = new Scanner(System.in);
        boolean bookCreation = true;
        boolean valid = false;
        int index = -1;

        //welcome message
        System.out.println("\t\tWelcome to the book program!");

        //creating books
        do{
            System.out.print("Would you like to create a book object? (yes/no): ");
            //checks for valid input
            do{
                input = myScan.nextLine().toUpperCase();
                if(!input.equals("YES") && !input.equals("NO")){
                    System.out.print("Oops! That’s not a valid entry. Please try again: ");
                    valid = false;
                }
                else{
                    valid = true;
                }
            }while(valid == false);

            //create a book object
            if(input.equals("YES")){
                //get author, title, and isbn from user, split entries by /, ensure no spaces at the end, ensure all entries are uppercase
                System.out.print("Enter the author, title, and the isbn of the book separated by /: ");
                input = myScan.nextLine();
                String[] entries = input.split("/");
                String author = entries[0].trim().toUpperCase();
                String title = entries[1].trim().toUpperCase();
                String isbn = entries[2].trim().toUpperCase();
                System.out.println("Got it!");

                //bookstore book or library book?
                System.out.print("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
                //checks for valid input
                do{
                    input = myScan.nextLine().toUpperCase();
                    if(!input.equals("BB") && !input.equals("LB")){
                        System.out.print("Oops! That’s not a valid entry. Please try again: ");
                        valid = false;
                    }
                    else{
                        System.out.println("Got it!");
                        valid = true;
                    }
                }while(valid == false);
                
                //getting bookstore book information
                if(input.equals("BB")){
                    System.out.print("Enter the list price of " + title + " by " + author + ": ");
                    double price = myScan.nextDouble();
                    myScan.nextLine(); //grabs newline to prevent any errors
                    boolean onSale;
                    double saleRate;

                    System.out.print("Is it on sale? (yes/no): ");
                    //checks for valid input
                    do{
                        input = myScan.nextLine().toUpperCase();
                        if(!input.equals("YES") && !input.equals("NO")){
                            System.out.print("Oops! That’s not a valid entry. Please try again: ");
                            valid = false;
                        }
                        else{
                            valid = true;
                        }
                    }while(valid == false);
                    //book is on sale
                    if(input.equals("YES")){
                        onSale = true;
                        System.out.print("Deduction percentage: ");
                        String rawPercent = myScan.nextLine();
                        //convert string into a mathematical value we can use to calculate discounted book price
                        rawPercent = rawPercent.replace("%", "");
                        saleRate = Double.parseDouble(rawPercent);
                        saleRate = saleRate / 100;
                    }
                    //book is not on sale
                    else{
                        onSale = false;
                        saleRate = 0.0;
                    }
                    System.out.println("Got it!");

                    //using all given info, add bookstore book to array
                    index++;
                    array[index] = new BookstoreBook(author, title, isbn, price, onSale, saleRate);

                    //print bookstore book toString
                    System.out.println("Here is your bookstore book information");
                    System.out.println(array[index]);
                }
                //getting library book information
                else{

                }
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
            return super.toString() + ", $" + String.format("%.2f", price) + " listed for $" + String.format("%.2f", calculateDiscountedPrice()) + "]";
        else
            return super.toString() + ", $" + String.format("%.2f", price) + "]";
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

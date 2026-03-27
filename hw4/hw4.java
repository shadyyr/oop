// Worked on by: Shade Rahman
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
                    System.out.print("Oops! That's not a valid entry. Please try again: ");
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
                        System.out.print("Oops! That's not a valid entry. Please try again: ");
                        valid = false;
                    }
                    else{
                        System.out.println("Got it!");
                        valid = true;
                    }
                }while(valid == false);
                
                //getting bookstore book information
                if(input.equals("BB")){
                    //initialize bookstore book variables
                    double price;
                    boolean onSale;
                    double saleRate;

                    //get price
                    System.out.print("Enter the list price of " + title + " by " + author + ": ");
                    price = myScan.nextDouble();
                    myScan.nextLine(); //grabs newline to prevent any errors

                    //get onSale and saleRate
                    System.out.print("Is it on sale? (yes/no): ");
                    //checks for valid input
                    do{
                        input = myScan.nextLine().toUpperCase();
                        if(!input.equals("YES") && !input.equals("NO")){
                            System.out.print("Oops! That's not a valid entry. Please try again: ");
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
                    System.out.println("Got it!\n");

                    //using all given info, add bookstore book to array
                    index++;
                    array[index] = new BookstoreBook(author, title, isbn, price, onSale, saleRate);

                    //print bookstore book toString
                    System.out.println("Here is your bookstore book information");
                    System.out.println(array[index]);
                    System.out.println("\n");
                }
                //getting library book information
                else{
                    //initialize library book variables
                    String Subject;
                    String SubjectCode = "";
                    String callNumber;

                    //get subject
                    System.out.print("What's the subject: ");
                    //checks for valid input
                    do{
                        Subject = myScan.nextLine();
                        //find subject code
                        switch(Subject.toUpperCase()){
                            case "GENERAL": SubjectCode = "A"; break;
                            case "PHILOSOPHY": SubjectCode = "B"; break;
                            case "RELIGION": SubjectCode = "C"; break;
                            case "WORLD HISTORY": SubjectCode = "D"; break;
                            case "HISTORY OF THE AMERICAS": SubjectCode = "E"; break;
                            case "GEOGRAPHY": SubjectCode = "F"; break;
                            case "ANTHROPOLOGY": SubjectCode = "G"; break;
                            case "SOCIAL SCIENCES": SubjectCode = "H"; break;
                            case "INTERNET": SubjectCode = "I"; break;
                            case "POLITICAL SCIENCE": SubjectCode = "J"; break;
                            case "LAW": SubjectCode = "K"; break;
                            case "EDUCATION": SubjectCode = "L"; break;
                            case "MUSIC": SubjectCode = "M"; break;
                            case "FINE ARTS": SubjectCode = "N"; break;
                            case "LANGUAGE": SubjectCode = "P"; break;
                            case "SCIENCE": SubjectCode = "Q"; break;
                            case "MEDICINE": SubjectCode = "R"; break;
                            case "AGRICULTURE": SubjectCode = "S"; break;
                            case "TECHNOLOGY": SubjectCode = "T"; break;
                            case "MILITARY": SubjectCode = "U"; break;
                            default: SubjectCode = "";
                        }
                        if(SubjectCode.equals("")){
                            System.out.print("Oops! That's not a valid entry. Please try again: ");
                            valid = false;
                        }
                        else{
                            System.out.println("Got it!\n");
                            valid = true;
                        }
                    }while(valid == false);

                    //generate callNumber
                    int topFloor = 15;
                    int bottomFloor = 1;
                    Random rand = new Random();
                    int floor = rand.nextInt(topFloor - bottomFloor + 1) + bottomFloor;
                    callNumber = SubjectCode + "." + String.format("%02d", floor) + "." + author.substring(0,3) + "." + isbn.charAt(isbn.length() - 1);
                    
                    //using all given info, add library book to array
                    index++;
                    array[index] = new LibraryBook(author, title, isbn, SubjectCode, callNumber);

                    //print library book toString
                    System.out.println("Here is your library book information");
                    System.out.println(array[index]);
                    System.out.println("\n");

                }
            }
            //display all books
            else{
                //counts all library books and bookstore books
                int LBcount = 0;
                int BBcount = 0;
                for (Book b : array){
                    if (b!= null && b.getBookType().equals("Library Book")){
                        LBcount++;
                    }
                    if (b!= null && b.getBookType().equals("Bookstore Book")){
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
        System.out.print("Would you like to search for a book? (yes/no): ");
        //checks for valid input
        do{
            input = myScan.nextLine().toUpperCase();
            if(!input.equals("YES") && !input.equals("NO")){
                System.out.print("Oops! That's not a valid entry. Please try again: ");
                valid = false;
            }
            else{
                valid = true;
            }
        }while(valid == false);
        //user wants to search for a book
        if(input.equals("YES")){
            System.out.print("Search by isbn, author, or title? ");
            input = myScan.nextLine().toUpperCase();
            int LBcount = 0;
            int BBcount = 0;

            if(input.equals("ISBN")){
                System.out.print("Enter the last character of the isbn: ");
                input = myScan.nextLine().toUpperCase();
                //figure out # of library books and # of bookstore books this applies to
                for(Book b : array){
                    if(b!= null && (b.getIsbn().charAt((b.getIsbn().length()) - 1)) == input.charAt(0)){
                        if((b.getBookType()).equals("Library Book")){
                            LBcount++;
                        }
                        else{
                            BBcount++;
                        }
                    }
                }
                //num of books found msg
                System.out.println("We found " + LBcount + " Library Book(s) and " + BBcount + " Book Store Book(s):");
                //print the books
                for(Book b : array){
                    if(b!= null && (b.getIsbn().charAt((b.getIsbn().length()) - 1)) == input.charAt(0)){
                        System.out.println(b);
                    }
                }
            }
            else if(input.equals("AUTHOR")){
                System.out.print("Enter the first three letters of the author: ");
                input = myScan.nextLine().toUpperCase();
                //figure out # of library books and # of bookstore books this applies to
                for(Book b : array){
                    if(b != null && ((b.getAuthor()).substring(0,3)).equals(input)){
                        if((b.getBookType()).equals("Library Book")){
                            LBcount++;
                        }
                        else{
                            BBcount++;
                        }
                    }
                }
                //num of books found msg
                System.out.println("We found " + LBcount + " Library Book(s) and " + BBcount + " Book Store Book(s):");
                //print books
                for(Book b : array){
                    if(b != null && ((b.getAuthor()).substring(0,3)).equals(input)){
                        System.out.println(b);
                    }
                }
            }
            else if(input.equals("TITLE")){
                System.out.print("Enter the title of the book: ");
                input = myScan.nextLine().toUpperCase();
                //figure out # of library books and # of bookstore books this applies to
                for(Book b : array){
                    if(b != null && (b.getTitle()).equals(input)){
                        if((b.getBookType()).equals("Library Book")){
                            LBcount++;
                        }
                        else{
                            BBcount++;
                        }
                    }
                }
                //num of books found msg
                System.out.println("We found " + LBcount + " Library Book(s) and " + BBcount + " Book Store Book(s):");
                //print books
                for(Book b : array){
                    if(b != null && (b.getTitle()).equals(input)){
                        System.out.println(b);
                    }
                }
            }
            //goodbye message
            System.out.println("\nTake Care!");
            return;
        }    
        //user does not want to search for a book
        else{
            //goodbye message
            System.out.println("\nTake Care!");
            return;
        }
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
    private String Subject; //this specifically holds the subject code, not the full subject name itself
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

    public LibraryBook(String author, String title, String isbn){
        super(author, title, isbn);
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

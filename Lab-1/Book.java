public class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;

    public Book() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.isbn = "000-0000000000";
        this.price = 0.0;
    }

    public Book(String title, String author, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("   [Book] " + title + " by " + author + " ($" + price + ")");
    }

    public String getTitle() {
        return title;
    }
}
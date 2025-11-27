import java.util.ArrayList;
import java.util.List;

public class Faculty extends Person {
    private List<Book> borrowedBooks;

    public Faculty() {
        super("Faculty Member", "faculty@uni.edu");
        this.borrowedBooks = new ArrayList<>();
    }

    public Faculty(String name, String email) {
        super(name, email);
        this.borrowedBooks = new ArrayList<>();
    }

    @Override
    public void borrowBook(Book b) {
        borrowedBooks.add(b);
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Faculty Record ---");
        System.out.println("Professor: " + this.name);
        System.out.println("Contact: " + this.email);
        System.out.println("Current Loans:");
        if (borrowedBooks.isEmpty()) {
            System.out.println("   No active loans.");
        } else {
            for (Book b : borrowedBooks) {
                b.displayInfo();
            }
        }
    }
}
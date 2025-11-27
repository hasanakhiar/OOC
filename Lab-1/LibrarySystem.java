public class LibrarySystem {

    public static int totalBooksIssued = 0;

    public static void issueBook(Person p, Book b) {
        p.borrowBook(b);
        totalBooksIssued++;
        System.out.println("System: Issued '" + b.getTitle() + "' to " + p.getClass().getSimpleName());
    }

    public void printPersonDetails(Person p) {
        p.displayDetails();
        System.out.println();
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        Book b1 = new Book("Intro to Java", "Herbert Schildt", "12345", 50.0);
        Book b2 = new Book("Data Science 101", "O'Reilly", "67890", 45.5);
        Book b3 = new Book("Arch Linux Handbook", "Community", "11223", 20.0);

        Person student = new Student("Alex", "alex@uni.edu");
        Person faculty = new Faculty("Dr. Smith", "smith@uni.edu");

        System.out.println("=== Issuing Phase ===");
        issueBook(student, b1);
        issueBook(student, b2);
        issueBook(faculty, b3);

        System.out.println("\n=== Reporting Phase ===");

        library.printPersonDetails(student);
        library.printPersonDetails(faculty);

        System.out.println("Total Books Issued in Library: " + LibrarySystem.totalBooksIssued);
    }
}
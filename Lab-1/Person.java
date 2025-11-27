public abstract class Person {
    protected String name;
    protected String email;

    public Person() {
        this.name = "No Name";
        this.name = "NULL";
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract void displayDetails();

    public abstract void borrowBook(Book b);

}
import java.util.ArrayList;
import java.util.List;

// --- 1. THE CONTRACTS (Framework Layer) ---

// Interface for naming behavior
interface Nameable {
    String getName();

    void setName(String name);
}

// Abstract Class: The Blueprint for ANY Shop
abstract class Shop {
    private CustList customerList; // Composition: Shop has a Customer List

    public Shop() {
        customerList = new CustList();
    }

    // Concrete Method: Shared Logic (Reuse)
    public void calculateSaleTax() {
        System.out.println("LOG: Calculating General Sales Tax (8.25%)...");
    }

    public void addCustomer(String name) {
        customerList.add(name);
    }

    public void showCustomers() {
        customerList.display();
    }

    // Abstract Methods: Forced Implementation (Customization)
    public abstract String[] getInventory();

    public abstract void buyInventory(String item);
}

// Composition Component
class CustList {
    private List<String> customers = new ArrayList<>();

    public void add(String name) {
        customers.add(name);
        System.out.println("Customer added: " + name);
    }

    public void display() {
        System.out.println("Customer List: " + customers);
    }
}

// --- 2. THE IMPLEMENTATION (Application Layer) ---

// Implementation 1: Pizza Shop
class PizzaShop extends Shop implements Nameable {
    private String shopName;
    private String[] menu = { "Pepperoni", "Cheese", "Veggie" };

    public String getName() {
        return shopName;
    }

    public void setName(String name) {
        this.shopName = name;
    }

    // Implementing the Shop contract
    public String[] getInventory() {
        return menu;
    }

    public void buyInventory(String item) {
        System.out.println("Buying Pizza Ingredients for: " + item);
    }
}

// Implementation 2: Donut Shop
class DonutShop extends Shop implements Nameable {
    private String shopName;
    private String[] menu = { "Glazed", "Jelly", "Chocolate" };

    public String getName() {
        return shopName;
    }

    public void setName(String name) {
        this.shopName = name;
    }

    // Implementing the Shop contract
    public String[] getInventory() {
        return menu;
    }

    public void buyInventory(String item) {
        System.out.println("Ordering Flour and Sugar for: " + item);
    }
}

// --- 3. THE DRIVER (Dynamic Loading) ---

public class ShopFrameworkDemo {
    public static void main(String[] args) {
        // In a real app, this comes from a config file
        // Try changing this string to "DonutShop"
        String className = "PizzaShop";

        System.out.println("Starting Shop Application...");

        try {
            // DYNAMIC INSTANTIATION
            // The code doesn't know it's a PizzaShop until runtime!
            Class<?> c = Class.forName(className);
            Object obj = c.getDeclaredConstructor().newInstance();

            // Casting to the Contract (Shop)
            Shop myShop = (Shop) obj;

            // Using Interface methods (if supported)
            if (myShop instanceof Nameable) {
                ((Nameable) myShop).setName("Best " + className + " in Town");
                System.out.println("Welcome to: " + ((Nameable) myShop).getName());
            }

            // Using Abstract Class methods
            myShop.calculateSaleTax(); // Shared code

            // Using the Composition component
            myShop.addCustomer("Alice");
            myShop.addCustomer("Bob");
            myShop.showCustomers();

            System.out.println("Menu:");
            for (String item : myShop.getInventory()) {
                System.out.println(" - " + item);
            }

        } catch (Exception e) {
            System.out.println("Failed to load shop: " + e.getMessage());
        }
    }
}
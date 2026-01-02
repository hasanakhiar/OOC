import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SYSTEM START: TASTEBUDS CATERING SYSTEM ===\n");

        // 1. Initialize System Data (Employees)
        HeadChef headChef = new HeadChef(101, "Gordon");
        DeliveryManager manager = new DeliveryManager(102, "Monica");

        Driver d1 = new Driver(201, "John (Bike)", "LIC-9999");
        Driver d2 = new Driver(202, "Doe (Van)", "LIC-8888");
        List<Driver> driverPool = new ArrayList<>(Arrays.asList(d1, d2));

        // 2. Initialize Customer (Registered, with 6 previous orders)
        Customer customer = new Customer(1, "Alice", true, 6);

        // Placing an Order
        Order order = customer.placeOrder("Wedding Platter", 200.00);

        // Main Page Display (Simulation)
        System.out.println("\n[Main Page Display]");
        System.out.println("Current Serving Order: #" + (order.getOrderId() - 1));

        // Kitchen Processing
        headChef.prepareOrder(order, true, "Team Alpha", "45 mins");

        // Delivery Assignment
        manager.assignDelivery(order, driverPool);

        // Driver Checkout
        Driver assignedDriver = order.getDriver();
        if (assignedDriver != null) {
            // Driver uses mobile app to validate license
            assignedDriver.checkoutOrder(order, "LIC-9999");
        }

        // Customer Feedback
        System.out.println("\n--- Order Delivered ---");
        customer.confirmDelivery(order, "Excellent service and timing!", 5);

        System.out.println("\n=== SYSTEM END: FINAL STATUS ===");
        System.out.println(order);
    }
}
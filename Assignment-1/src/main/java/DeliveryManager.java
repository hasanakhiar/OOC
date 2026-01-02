import java.util.List;

public class DeliveryManager extends Employee {
    public DeliveryManager(int id, String name) {
        super(id, name);
    }

    public void assignDelivery(Order order, List<Driver> drivers) {
        System.out.println("\n--- Delivery Assignment (Manager: " + this.name + ") ---");

        Driver selectedDriver = null;

        if (order.getPriority() == OrderPriority.PRIORITY) {
            System.out.println("NOTE: Priority Order detected. Arranging express vehicle (<10 mins).");
        } else {
            System.out.println("NOTE: Normal Order. Checking standard availability.");
        }

        // Find first available driver
        for (Driver d : drivers) {
            if (d.isAvailable()) {
                selectedDriver = d;
                break;
            }
        }

        if (selectedDriver != null) {
            order.assignDriver(selectedDriver);
            selectedDriver.setAvailable(false); // Driver is now busy
            System.out.println("ACTION: Assigned Driver " + selectedDriver.getName());
        } else {
            System.out.println("ALERT: No drivers available at this moment!");
        }
    }
}
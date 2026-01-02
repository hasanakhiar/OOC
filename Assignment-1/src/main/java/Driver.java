public class Driver extends Employee {
    private String licenseNumber;
    private boolean isAvailable = true;

    public Driver(int id, String name, String licenseNumber) {
        super(id, name);
        this.licenseNumber = licenseNumber;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void checkoutOrder(Order order, String inputLicense) {
        System.out.println("\n--- Driver Checkout (Driver: " + this.name + ") ---");

        if (!this.licenseNumber.equals(inputLicense)) {
            System.out.println("ERROR: License verification failed! Access Denied.");
            return;
        }

        if (order.getDriver() != this) {
            System.out.println("ERROR: This order is not assigned to you.");
            return;
        }

        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        System.out.println("SUCCESS: Validation Passed.");
        System.out.println("STATUS UPDATE: Order #" + order.getOrderId() + " is OUT FOR DELIVERY.");
    }
}
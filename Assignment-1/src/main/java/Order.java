public class Order {
    private static int orderCounter = 0;

    private int orderId;
    private int queueNumber;
    private Customer customer;
    private String items;
    private double totalAmount;

    private OrderStatus status;
    private OrderPriority priority;


    private String chefAssigned;
    private String estimatedTime;


    private Driver driver;


    private String feedback;
    private int rating;

    public Order(Customer customer, String items, double amount) {
        this.orderId = ++orderCounter;
        this.queueNumber = orderId;
        this.customer = customer;
        this.items = items;
        this.totalAmount = amount;
        this.status = OrderStatus.PLACED;
        this.priority = OrderPriority.NORMAL; // Default
    }


    public int getOrderId() { return orderId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double amount) { this.totalAmount = amount; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public OrderPriority getPriority() { return priority; }
    public void setPriority(OrderPriority priority) { this.priority = priority; }

    public Driver getDriver() { return driver; }
    public void assignDriver(Driver driver) { this.driver = driver; }

    public void setKitchenDetails(String chef, String time) {
        this.chefAssigned = chef;
        this.estimatedTime = time;
    }

    public void setFeedback(String comment, int rating) {
        this.feedback = comment;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " (" + items + ") | Status: " + status + " | Priority: " + priority;
    }
}
public class Customer extends User {
    private boolean isRegistered;
    private int monthlyOrderCount;

    public Customer(int id, String name, boolean isRegistered, int previousOrders) {
        super(id, name);
        this.isRegistered = isRegistered;
        this.monthlyOrderCount = previousOrders;
    }

    public Order placeOrder(String items, double rawPrice) {
        double finalPrice = rawPrice;

        // Discount Logic
        if (isRegistered) {
            if (monthlyOrderCount > 10) {
                finalPrice *= 0.90; // 10% off
            } else if (monthlyOrderCount > 5) {
                finalPrice *= 0.95; // 5% off
            }
        }

        Order newOrder = new Order(this, items, finalPrice);
        monthlyOrderCount++; // Increment count for next time

        System.out.println("LOG: Customer " + this.name + " placed order.");
        System.out.println("   -> Item: " + items);
        System.out.println("   -> Total Bill: $" + String.format("%.2f", finalPrice));
        System.out.println("   -> Queue Position: " + newOrder.getOrderId());

        return newOrder;
    }

    public void confirmDelivery(Order order, String comments, int rating) {
        order.setStatus(OrderStatus.DELIVERED);
        order.setFeedback(comments, rating);
        System.out.println("LOG: Customer confirmed delivery.");
        System.out.println("   -> Rating: " + rating + "/5");
        System.out.println("   -> Comment: \"" + comments + "\"");
    }
}
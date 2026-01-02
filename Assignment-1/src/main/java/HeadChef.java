public class HeadChef extends Employee {
    public HeadChef(int id, String name) {
        super(id, name);
    }

    public void prepareOrder(Order order, boolean makePriority, String assignedChefs, String estTime) {
        System.out.println("\n--- Kitchen Preparation (Head Chef: " + this.name + ") ---");
        System.out.println("Reviewing Order #" + order.getOrderId());

        if (makePriority) {
            order.setPriority(OrderPriority.PRIORITY);
            System.out.println("ACTION: Marked as PRIORITY order.");
        }

        order.setKitchenDetails(assignedChefs, estTime);
        order.setStatus(OrderStatus.PREPARING);

        System.out.println("ACTION: Assigned to " + assignedChefs);
        System.out.println("ACTION: Estimated time " + estTime);

        order.setStatus(OrderStatus.READY_FOR_DELIVERY);
        System.out.println("STATUS UPDATE: Order is READY FOR DELIVERY.");
    }
}
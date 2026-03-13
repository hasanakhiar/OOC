public class ComplaintTask implements Runnable {
    private VibeGamingCustomer customer;

    public ComplaintTask(VibeGamingCustomer customer) {
        this.customer = customer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            String complaintMessage = "Complaint no: " + (i + 1) + " filed by " + Thread.currentThread().getName();
            customer.launchComplaint(complaintMessage);
           // VibeGamingCustomer.incrementTotalComplaints();
        }
    }
}
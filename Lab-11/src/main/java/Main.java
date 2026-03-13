public class Main {
    public static void main(String[] args) {
        VibeGamingCustomer customerA = new VibeGamingCustomer();
        VibeGamingCustomer customerB = new VibeGamingCustomer();

        Thread t1 = new Thread(new ComplaintTask(customerA), "Thread-A1");
        Thread t2 = new Thread(new ComplaintTask(customerA), "Thread-A2");

        Thread t3 = new Thread(new ComplaintTask(customerB), "Thread-B1");
        Thread t4 = new Thread(new ComplaintTask(customerB), "Thread-B2");

        //Thread t5 = new Thread()
        //Thread t6 = new Thread()

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.err.println("A thread was interrupted: " + e.getMessage());
        }

        System.out.println("Customer A complaints: " + customerA.getComplaintsCount() + " (Expected: 2000)");
        System.out.println("Customer B complaints: " + customerB.getComplaintsCount() + " (Expected: 2000)");
        System.out.println("Global Total Complaints: " + VibeGamingCustomer.getTotalComplaints() + " (Expected: 4000)");
    }
}
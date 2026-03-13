import java.util.ArrayList;

public class VibeGamingCustomer {
    private ArrayList<String> complaints = new ArrayList<>();

    private static int totalComplaints = 0;



    public synchronized void launchComplaint(String complaint) {
        complaints.add(complaint);

    }

    public static synchronized void incrementTotalComplaints() {
        totalComplaints++;
    }

    public int getComplaintsCount() {
        return complaints.size();
    }

    public static int getTotalComplaints() {
        return totalComplaints;
    }
}
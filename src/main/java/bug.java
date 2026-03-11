public class Bug {
    private int id;
    private String description;
    private String status;
    private int priority;
    private String assignedTo;
    private String environment;
    private long creationTimestamp;
    private long lastUpdatedTimestamp;
    private static int bugCount = 0;
    public Bug(int id, String description, String assignedTo, String environment) {
        this.id = id;
        this.description = description;
        this.assignedTo = assignedTo;
        this.environment = environment;
        this.status = "Open";
        this.priority = 1;
        this.creationTimestamp = System.currentTimeMillis();
        this.lastUpdatedTimestamp = System.currentTimeMillis();
        bugCount++;
    }
    public Bug(int id, String description) {
        this(id, description, "Unassigned", "Default");
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
        updateTimestamp();
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        updateTimestamp();
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
        updateTimestamp();
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
        updateTimestamp();
    }
    public String getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        updateTimestamp();
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
        updateTimestamp();
    }
    public long getCreationTimestamp() {
        return creationTimestamp;
    }
    public long getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    private void updateTimestamp() {
        this.lastUpdatedTimestamp = System.currentTimeMillis();
    }
    public static int getBugCount() {
        return bugCount;
    }
    public boolean isOpen() {
        return this.status.equals("Open");
    }
    public boolean isClosed() {
        return this.status.equals("Closed");
    }
    public void closeBug() {
        setStatus("Closed");
    }
    public void reopenBug() {
        setStatus("Reopened");
    }
    public String getDetails() {
        return "Bug ID: " + id + ", Desc: " + description + ", Status: " + status + ", Priority: " + priority + ", Assigned: " + assignedTo + ", Env: " + environment;
    }
    @Override
    public String toString() {
        return getDetails();
    }
    public static void main(String[] args) {
        Bug bug1 = new Bug(1, "UI element misaligned", "Alice", "Staging");
        Bug bug2 = new Bug(2, "Database connection error", "Bob", "Production");
        bug1.setPriority(2);
        bug2.setStatus("In Progress");
        bug1.move();
        bug2.turn();
        System.out.println(bug1.getDetails());
        System.out.println(bug2.getDetails());
        System.out.println("Total bugs created: " + getBugCount());
        Bug bugsy = new Bug(10);
        bugsy.move();
        bugsy.move();
        System.out.println(bugsy.getPosition());
        bugsy.turn();
        bugsy.move();
        System.out.println(bugsy.getPosition());
        System.out.println("Bugsy Details: " + bugsy);
    }
    // The following methods are taken from a common "Bug" class exercise.
    private int currentPosition;
    private boolean direction = true; // true for right, false for left
    public Bug(int initialPosition) {
        this.currentPosition = initialPosition;
        bugCount++;
    }
    public int getPosition() {
        return currentPosition;
    }
    public void move() {
        if (direction) {
            currentPosition = currentPosition + 1;
        } else {
            currentPosition = currentPosition - 1;
        }
    }
    public void turn() {
        direction = !direction;
    }
}

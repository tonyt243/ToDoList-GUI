public class Task {
    private String description;
    private String priority;
    private String date;
    private boolean completed;

    public Task(String description, String priority, String date) {
        this.description = description;
        this.priority = priority.toUpperCase();
        this.date = date;
        this.completed = false;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void unmarkCompleted() {
        this.completed = false;
    }


    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return status + " " + description + " (" + priority + ") - Due: " + date;
    }
}

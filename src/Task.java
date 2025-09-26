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

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return status + " " + description + " (" + priority + ") - Due: " + date;
    }

    //Convert task to text line for saving
    public String toFileString() {
        String status = completed ? "Completed" : "Incomplete";
        return "TASK LIST:\n\n" + description + " ; " + priority + " ; " + date + " ; " + status;
    }

    //Rebuild a task from text line
    public static Task fromFileString(String line) {
        String[] parts = line.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid task line: " + line);
        }
        Task task = new Task(parts[0], parts[1], parts[2]);
        if (Boolean.parseBoolean(parts[3])) {
            task.markCompleted();
        }
        return task;
    }
}

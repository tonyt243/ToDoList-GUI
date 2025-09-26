import java.io.*;
import java.util.*;

public class TaskStorage {
    private static final String FILE_NAME = "tasks.txt";

    //Save tasks to file
    public static void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load tasks from file
    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tasks; // no file yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.fromFileString(line);
                    tasks.add(task);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Skipping invalid task line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoListApp {
    private DefaultListModel<Task> taskModel;
    private JList<Task> taskList;
    private JTextField descriptionField, dateField;
    private JComboBox<String> priorityBox;
    private ArrayList<Task> tasks;

    public ToDoListApp() {
        tasks = new ArrayList<>();

        JFrame frame = new JFrame("To Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);

        // Task list
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Input fields
        descriptionField = new JTextField(15);
        dateField = new JTextField(10);
        priorityBox = new JComboBox<>(new String[]{"HIGH", "MED", "LOW"});

        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Task");
        JButton completeButton = new JButton("Mark Completed");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Due Date (mm/dd/yyyy):"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Priority:"));
        inputPanel.add(priorityBox);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add task
        addButton.addActionListener(e -> {
        String desc = descriptionField.getText().trim();
        String date = dateField.getText().trim();
        String priority = (String) priorityBox.getSelectedItem();

        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Description cannot be empty.");
        return;
        }

        if (!InputValidator.isValidDate(date)) {
            JOptionPane.showMessageDialog(frame, "Invalid date! Use mm/dd/yyyy format.");
        return;
        }

        Task newTask = new Task(desc, priority, date);
        tasks.add(newTask);
        taskModel.addElement(newTask);
        descriptionField.setText("");
        dateField.setText("");
    });


        // Remove task
        removeButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                tasks.remove(selected);
                taskModel.remove(selected);
            }
        });

        // Mark completed
        completeButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                Task task = tasks.get(selected);
                if (task.isCompleted()){
                    task.unmarkCompleted();
                }else{
                    task.markCompleted();
                }
                taskModel.set(selected, task);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListApp::new);
    }
}

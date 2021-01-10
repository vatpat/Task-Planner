import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.*;

public class TaskManager extends Frame {
  private Label appHeader;
  private TextField enterFieldTask;
  private TextField enterFieldDate;
  private Label taskFieldLabel;
  private Label taskDateLabel;
  private Button submitButton;
  private Button removeButton;
  private Label errorMessage;
  private Label space;
  private TextArea tasks;
  private TaskList tasklist;


  public TaskManager() {
    // General Properties
    setTitle("Task Planner");
    setSize(800, 400);
    setResizable(false);

    // Initialize TaskList
    tasklist = new TaskList();

    // Set Layout
    GridBagConstraints constraints = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);

    // Make components fill the application
    constraints.fill = GridBagConstraints.BOTH;

    // Resize factor
    constraints.weightx = 1.0;

    // Header
    constraints.gridx = 0;
    constraints.gridy = 0;
    appHeader = new Label("Task Manager");
    appHeader.setFont(new Font("Serif", Font.BOLD, 30));
    layout.setConstraints(appHeader, constraints);
    add(appHeader);

    // Enter Description Label
    constraints.gridx = 0;
    constraints.gridy = 2;
    taskFieldLabel = new Label("Enter the Task Description: ");
    taskFieldLabel.setAlignment(Label.LEFT);
    layout.setConstraints(taskFieldLabel, constraints);
    add(taskFieldLabel);

    // Enter Description Field
    constraints.gridx = 0;
    constraints.gridy = 3;
    enterFieldTask = new TextField();
    enterFieldTask.setColumns(50);
    layout.setConstraints(enterFieldTask, constraints);
    add(enterFieldTask);

    // Enter Date Label
    constraints.gridx = 0;
    constraints.gridy = 5;
    taskDateLabel = new Label("Enter the Due Date as MM/DD/YYYY: ");
    taskDateLabel.setAlignment(Label.LEFT);
    layout.setConstraints(taskDateLabel, constraints);
    add(taskDateLabel);

    // Enter Date Field
    constraints.gridx = 0;
    constraints.gridy = 6;
    enterFieldDate = new TextField();
    enterFieldDate.setColumns(50);
    layout.setConstraints(enterFieldDate, constraints);
    add(enterFieldDate);

    // Add Button + Action Listener
    constraints.gridx = 0;
    constraints.gridy = 8;
    submitButton = new Button("Add");
    layout.setConstraints(submitButton, constraints);
    add(submitButton);
    submitButton.addActionListener(new AddButtonEvent());

    // Remove Button + Action Listener
    constraints.gridx = 0;
    constraints.gridy = 9;
    removeButton = new Button("Remove");
    layout.setConstraints(removeButton, constraints);
    add(removeButton);
    removeButton.addActionListener(new RemoveButtonEvent());

    // Error Message Label
    constraints.gridx = 0;
    constraints.gridy = 10;
    errorMessage = new Label();
    layout.setConstraints(errorMessage, constraints);
    add(errorMessage);

    // Task List Label
    constraints.gridx = 0;
    constraints.gridy = 12;
    space = new Label("Tasks:");
    layout.setConstraints(space, constraints);
    add(space);

    // Task List Field
    constraints.gridx = 0;
    constraints.gridy = 13;
    tasks = new TextArea(tasklist.toString(), 0, 20);
    tasks.setEditable(false);
    layout.setConstraints(tasks, constraints);
    add(tasks);

    // Handling window closing when exited
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });

    // Set Visible
    setVisible(true);
  }

  public static void main(String[] args) {
    TaskManager t = new TaskManager();
  }

  /**
   * The AddButtonEvent class handles the response to the Add button being pressed
   */
  class AddButtonEvent implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String desc = enterFieldTask.getText();
        String date = enterFieldDate.getText();
        tasklist.insert(new Task(desc, date));
        errorMessage.setText("");
        enterFieldTask.setText("");
        enterFieldDate.setText("");
      } catch (IllegalArgumentException iae) {
        errorMessage.setText(iae.getMessage());
      }
      tasks.setText(tasklist.toString());
    }
  }

  /**
   * The RemoveButtonEvent class handles the response to the Remove button being pressed
   */
  class RemoveButtonEvent implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String desc = enterFieldTask.getText();
        String date = enterFieldDate.getText();
        tasklist.remove(new Task(desc, date));
        errorMessage.setText("");
        enterFieldTask.setText("");
        enterFieldDate.setText("");
      } catch (IllegalArgumentException iae) {
        errorMessage.setText(iae.getMessage());
      }
      tasks.setText(tasklist.toString());
    }
  }
}

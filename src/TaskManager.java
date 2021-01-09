import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.*;

public class TaskManager extends Frame {
  private TaskManager self;
  private Label appHeader;
  private TextField enterFieldTask;
  private TextField enterFieldDate;
  private Label taskFieldLabel;
  private Label taskDateLabel;
  private Button submitButton;
  private Label errorMessage;
  private Label space;
  private TextArea tasks;
  private TaskList tasklist;


  public TaskManager() {
    self = this;
    //this.setMinimumSize(new Dimension(800, 300));
    tasklist = new TaskList();

    GridBagConstraints constraints = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);

    // Make components fill the application
    constraints.fill = GridBagConstraints.BOTH;

    // Resize factor
    constraints.weightx = 1.0;

    constraints.gridx = 0;
    constraints.gridy = 0;
    appHeader = new Label("Task Manager");
    // appHeader.setAlignment(Label.CENTER);
    appHeader.setFont(new Font("Serif", Font.BOLD, 30));
    layout.setConstraints(appHeader, constraints);
    add(appHeader);

    constraints.gridx = 0;
    constraints.gridy = 2;
    taskFieldLabel = new Label("Enter the Task Description: ");
    taskFieldLabel.setAlignment(Label.LEFT);
    layout.setConstraints(taskFieldLabel, constraints);
    add(taskFieldLabel);

    constraints.gridx = 0;
    constraints.gridy = 3;
    enterFieldTask = new TextField();
    enterFieldTask.setColumns(50);
    layout.setConstraints(enterFieldTask, constraints);
    add(enterFieldTask);

    constraints.gridx = 0;
    constraints.gridy = 5;
    taskDateLabel = new Label("Enter the Due Date as MM/DD/YYYY: ");
    taskDateLabel.setAlignment(Label.LEFT);
    layout.setConstraints(taskDateLabel, constraints);
    add(taskDateLabel);

    constraints.gridx = 0;
    constraints.gridy = 6;
    enterFieldDate = new TextField();
    enterFieldDate.setColumns(50);
    layout.setConstraints(enterFieldDate, constraints);
    add(enterFieldDate);

    constraints.gridx = 0;
    constraints.gridy = 8;
    submitButton = new Button("Add");
    layout.setConstraints(submitButton, constraints);
    add(submitButton);
    submitButton.addActionListener(new AddButtonEvent());

    constraints.gridx = 0;
    constraints.gridy = 9;
    errorMessage = new Label();
    layout.setConstraints(errorMessage, constraints);
    add(errorMessage);

    constraints.gridx = 0;
    constraints.gridy = 11;
    space = new Label("Tasks:"); 
    layout.setConstraints(space, constraints);
    add(space);
    
    constraints.gridx = 0;
    constraints.gridy = 12;
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

    setTitle("Task Planner");
    setSize(800, 400);
    setVisible(true);
  }

  public static void main(String[] args) {
    TaskManager t = new TaskManager();
  }

  class AddButtonEvent implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        String desc = enterFieldTask.getText();
        String date = enterFieldDate.getText();
        tasklist.insert(new Task(desc, date));
        errorMessage.setText("");
      } catch (IllegalArgumentException iae) {
        errorMessage.setText(iae.getMessage());
      }
      tasks.setText(tasklist.toString());
    }
  }
}

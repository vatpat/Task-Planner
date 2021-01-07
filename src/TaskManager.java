import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskManager extends Frame {
  public TaskManager() {
    setTitle("Task Planner");
    setSize(700, 700);
    
    // Handling window closing when exited
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });
    
    setLayout(null); // no layout
    setVisible(true);
  }
  
  public static void main(String[] args) {
    TaskManager t = new TaskManager();
    // System.out.println(Task.checkDateValid(02, 28, 2021));
  }
}

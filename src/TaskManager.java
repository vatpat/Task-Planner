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
    // TaskManager t = new TaskManager();
    // System.out.println(Task.checkDateValid(02, 28, 2021));
    // Task a = new Task("desc", "12/30/2020");
    // Task b = new Task("desc", "12/20/2020");
    // System.out.print(a.compareTo(b));
    // TaskList t = new TaskList();
    // t.insert(a);
    // t.insert(b);
    
    // System.out.println("LIST: \n" + t.toString());
  }
}

import java.util.ArrayList;

/**
 * The TaskList class maintains a sorted ArrayList of Tasks that is updated whenever the user enters
 * a new task
 * 
 * @author vatrp
 *
 */
public class TaskList {
  private ArrayList<Task> tasks; // Sorted ArrayList of Tasks

  /**
   * Constructor for the TaskList class, initializes the tasks ArrayList to an empty list
   */
  public TaskList() {
    this.tasks = new ArrayList<Task>();
  }

  /**
   * Inserts the new Task to the correct position in the ArrayList
   * 
   * @param newTask The new Task to be inserted into the ArrayList
   */
  public void insert(Task newTask) {
    // If ArrayList is empty, add new Task as first element
    if (this.tasks.size() == 0) {
      this.tasks.add(newTask);
      return;
    }

    // Otherwise, find the first element in the list that is greater than the newTask
    int current = 0;
    while (current < this.tasks.size() && this.tasks.get(current).compareTo(newTask) <= 0) {
      if(this.tasks.get(current).equals(newTask)) {
        throw new IllegalArgumentException("Task Already Added");
      }
      current++;
    }

    // Insert new Task at that index
    this.tasks.add(current, newTask);
  }

  /**
   * Removes the specified task from the ArrayList
   * 
   * @param removedTask The task to be removed from the ArrayList
   */
  public void remove(Task removedTask) {
    boolean removed = this.tasks.remove(removedTask);
    if(!removed) {
      throw new IllegalArgumentException("No Such Task");
    }
  }

  /**
   * Returns a String representation of the TaskList
   * 
   * @return A String representation of the TaskList
   */
  public String toString() {
    String ret = "";
    for (Task t : this.tasks) {
      ret += t.toString();
      ret += "\n";
    }
    return ret;
  }
}

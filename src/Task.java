import java.util.Calendar;
import java.util.Date;

/**
 * The Task class represents a task that the user will provide information about. Namely, tasks have
 * a description and a due date
 * 
 * @author vatrp
 *
 */
public class Task implements Comparable<Task> {
  private String description; // The description of the Task
  private int day; // The day that the Task is due
  private int month; // The month that the Task is due
  private int year; // The year that the Task is due

  /**
   * Constructs a new Task based on the provided description and due that
   * 
   * @param description A description of the Task
   * @param dueDate     The date that the Task is due, formatted as MM/DD/YYYY
   */
  public Task(String description, String dueDate) {
    // Ensure description and dueDate are not empty
    if (description.equals("") || dueDate.equals("")) {
      throw new IllegalArgumentException("Description and Due Date can not be empty");
    }

    // Check date is separated by dashes
    String[] parsed = dueDate.split("/");
    if (parsed.length != 3) {
      throw new IllegalArgumentException("Due Date is not formatted as MM/DD/YYYY");
    }
    int m = Integer.parseInt(parsed[0]);
    int d = Integer.parseInt(parsed[1]);
    int y = Integer.parseInt(parsed[2]);
    if (!Task.checkDateValid(m, d, y)) {
      throw new IllegalArgumentException("Due Date is not formatted as MM/DD/YYYY");
    }
    this.month = m;
    this.day = d;
    this.year = y;
    this.description = description;
  }

  /**
   * Accessor method for the year of Task's due date
   * 
   * @return The Year that the Task is due, in numerical form
   */
  public int getYear() {
    return this.year;
  }

  /**
   * Accessor method for the month of the Task's due date
   * 
   * @return The Month that the Task is due, in numerical form
   */
  public int getMonth() {
    return this.month;
  }

  /**
   * Accessor method for the day that the Task is due
   * 
   * @return The Day that the Task is due, in numerical form
   */
  public int getDay() {
    return this.day;
  }

  /**
   * Checks if the provided date is valid
   * 
   * @param month The month of the date
   * @param day   The day of the date
   * @param year  The year of the
   * @return True if the provided month, day, and year are all valid. False otherwise
   */
  public static boolean checkDateValid(int month, int day, int year) {
    // Check if the date is valid using a Calendar
    Calendar c = Calendar.getInstance();
    c.setLenient(false); // Invalid dates should not be handled with leniency
    c.set(year, month - 1, day); // Our month is 1-based whereas set uses a 0-based month
    try {
      Date time = c.getTime(); // Attempt to get the time
      return true; // If there isn't an Exception then the date is valid
    } catch (Exception e) {
      return false; // Exception thrown - date is not valid
    }
  }

  /**
   * Returns a negative number if this task is due sooner than other, a positive number if the other
   * task is due sooner, or 0 if the tasks are due on the same day
   */
  @Override
  public int compareTo(Task other) {
    // This task is less than other if this task's due date comes before other's due date
    // Year:
    if (this.year != other.year) {
      return this.year - other.year;
    } else {
      // Month:
      if (this.month != other.month) {
        return this.month - other.month;
      } else {
        // Day:
        return this.day - other.day;
      }
    }
  }

  /**
   * Returns true if other is an instance of Task and has the same description and due date as this
   * Task
   * 
   * @return True if this Task and other have the same description and due date
   */
  @Override
  public boolean equals(Object other) {
    if(other instanceof Task) {
      Task otherTask = (Task) other;
      if(this.compareTo(otherTask) == 0 && this.description.equals(otherTask.description)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a String representation of the Task
   * 
   * @return A String representation of the Task
   */
  public String toString() {
    return this.month + "/" + this.day + "/" + this.year + " " + this.description;
  }
}

import java.util.Scanner;

public class Task {

    private String task;
    private String date;
    private final int Max_Size = 50;
    private boolean isDone;
// constructor for Task object. user may only enter tasks that are less than 50 characters
    public Task (String taskIn, String dateIn){
        while (taskIn.length() > Max_Size ){
            System.out.println("task must be less than 50 characters");
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please enter a new task");
            taskIn = keyboard.nextLine();
        }
        task = taskIn;
        date = dateIn;
        isDone = false;
    }
    //     getter method for task
    public String getTask(){
        return task;
    }
    //     getter method for date
    public String getDate(){
        return date;
    }
    //  method that allows user to set a task less than 50 characters, as of 10/20 commit this method is not used.
    public void setTask(String taskIn){
        while (taskIn.length() > Max_Size ){
            System.out.println("task must be less than 50 characters");
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please enter a new task");
            taskIn = keyboard.nextLine();
        }
        task =taskIn;
    }
    //    allows user to set the date of the task
    public void setDate(String dateIn){
        date = dateIn;
    }
    // allows user to set the task as done
    public void setTaskCompleted(){
        isDone = true;
    }
    //    getter method for done
    public Boolean getIsDone(){
        return isDone;
    }
    @Override
    public String toString(){
        return "("+ task +", "+date+")";
    }
@Override
    public boolean equals(Object objIn){
        Task taskIn = (Task) objIn;
        return task.equalsIgnoreCase(taskIn.task);
}
@Override
    public int hashCode(){
        return task.hashCode();
}

}

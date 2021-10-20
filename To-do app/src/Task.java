import java.util.Scanner;

public class Task {

    private String task;
    private String date;
    private final int Max_Size = 50;
    private boolean isDone;

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
    public String getTask(){
        return task;
    }
    public String getDate(){
        return date;
    }
    public boolean setTask(String taskIn){
        if (taskIn.length() > Max_Size ){
            System.out.println("task must be less than 50 characters");
        }else {
            task = taskIn;
        }
        return false;
    }
    public void setDate(String dateIn){
        date = dateIn;
    }

    public void setTaskCompleted(){
        isDone = true;
    }
    public Boolean getIsDone(){
        return isDone;
    }


}

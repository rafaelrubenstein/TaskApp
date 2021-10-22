import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    //     initializes a List
    List<Task> taskArrayList = new ArrayList<>();
    // allows user to add a new task object to the array list
    public void addItem(String taskIn,String dateIn){
        taskArrayList.add(new Task(taskIn,dateIn));
    }
    //     helper method that retrieves the index of a Task object based off the task name
    private int search(String taskIn){
        for (int i =0; i < taskArrayList.size();i++){
            Task tempTask = taskArrayList.get(i);
            String tempTaskName = tempTask.getTask();
            if(tempTaskName.equalsIgnoreCase(taskIn)){
                return i;
            }
        }
        return -999;
    }
    //     prints the list in neat columns.
    public void printList(){
        System.out.printf("%-50s%-10s\n","Task","Due date");
        for (Task task : taskArrayList) {
            System.out.printf("%-50s%-10s\n",task.getTask(),task.getDate());
        }

    }
    //     allows the user to get a specific task in the list
    public Task getTaskInList (String taskIn){
        //         helper method used to find the index
       int index = search(taskIn);

        if (index !=-999){
             return taskArrayList.get(index);
           }
        return null;
    }

    // method allows user to complete a task and removes that task from the list
    public void taskCompleted(String taskIn){
        getTaskInList(taskIn).setTaskCompleted();
        taskArrayList.remove(search(taskIn));
    }
//    method to allow user to write the list to a file
   public static void writeList(TaskList taskArrayListIn){
        try (
                FileWriter taskFile = new FileWriter("Tasks.txt");
                PrintWriter taskWriter = new PrintWriter(taskFile))
        {
            for (Task task: taskArrayListIn.taskArrayList){
                taskWriter.println(task.getTask());
                taskWriter.println(task.getDate());
            }
        }catch (IOException e){
            System.out.println("There was a problem writing the file");
        }
    }
//    method that allows program to read that file to the list in main
    public static void readList(TaskList taskArrayListIn){
        String tempTask;
        String tempDate;
        try(FileReader taskFile = new FileReader("Tasks.txt");
            BufferedReader taskStream = new BufferedReader(taskFile)) {
            tempTask = taskStream.readLine();
            while (tempTask != null){
                tempDate = taskStream.readLine();
                taskArrayListIn.addItem(tempTask,tempDate);
                tempTask = taskStream.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("no file was read");
        }catch (IOException e){
            System.out.println("there was an error with the file");
        }
    }
}


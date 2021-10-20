import java.util.ArrayList;
import java.util.List;

public class TaskList {
//     initializes a List which is as of 10/20 an array list,  list can be changed to a linked list later on if it makes more sense
    List<Task> taskArrayList = new ArrayList<>();
// allows user to add a new task object to the array list
    public void addItem(String taskIn,String dateIn){
        taskArrayList.add(new Task(taskIn,dateIn));
    }
//     helper method that retrievs the index of a Task object based off the task name 
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
//     allows the user to get a specifc task in the list 
    public Task getTaskInList (String taskIn){
//         helper method used to find the index
       int index = search(taskIn);

        if (index !=-999){
             return taskArrayList.get(index);
           }
//         may change this to an Optional to avoid nullpointer expectation 
        return null;
    }
// method allows user to complete a task and removes that task from the list
    public void taskCompleted(String taskIn){
        getTaskInList(taskIn).setTaskCompleted();
        taskArrayList.remove(search(taskIn));
    }

}


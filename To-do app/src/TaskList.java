import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskArrayList = new ArrayList<>();

    public void addItem(String taskIn,String dateIn){
        taskArrayList.add(new Task(taskIn,dateIn));
    }
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
    public void printList(){
        System.out.printf("%-50s%-10s\n","Task","Due date");
        for (Task task : taskArrayList) {
            System.out.printf("%-50s%-10s\n",task.getTask(),task.getDate());
        }

    }
    public Task getTaskInList (String taskIn){
       int index = search(taskIn);

        if (index !=-999){
             return taskArrayList.get(index);
           }
        return null;
    }

    public void taskCompleted(String taskIn){
        getTaskInList(taskIn).setTaskCompleted();
        taskArrayList.remove(search(taskIn));
    }

}


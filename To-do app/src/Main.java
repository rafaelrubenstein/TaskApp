public class Main {

    public static void main (String[]args){
//        initializes an empty task list
        TaskList task1 = new TaskList();
//        reads the file
        TaskList.readList(task1);

        String choice;
        do{
            System.out.println("Options menu");
            System.out.println("1: add a task");
            System.out.println("2: change the due date of a task");
            System.out.println("3: set the task as complete");
            System.out.println("4: print all tasks");
            System.out.println("5: exit");
            choice= EasyScanner.nextString();

            switch (choice) {
                case "1" -> addTask(task1);
                case "2" -> changeDate(task1);
                case "3" -> taskComplete(task1);
                case "4" -> task1.printList();
                case "5" -> TaskList.writeList(task1);
                default -> System.out.println("please enter a number one through 5");
            }
        }while (!choice.equals("5"));

    }
//    allows user to add a task
    static void addTask(TaskList taskListIn){
        String task,date;
        System.out.println("please enter a task");
        task =EasyScanner.nextString();
        System.out.println("please enter a due date");
        date = EasyScanner.nextString();
        taskListIn.addItem(task.trim(),date.trim());
    }
//    allows user to change the due date of the task
    static void changeDate(TaskList taskList){
        String task,date;
        System.out.println("please enter the task");
        task =EasyScanner.nextString();
        System.out.println("Please enter the new due date");
        date = EasyScanner.nextString();
        taskList.getTaskInList(task.trim()).setDate(date.trim());
    }
//    allows the user to set the task as complete, this will remove it from the list
    static void taskComplete(TaskList taskList){
        System.out.println("please enter the task");
        String task = EasyScanner.nextString();
        taskList.taskCompleted(task.trim());
    }
}

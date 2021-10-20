import java.util.Scanner;
// As of 10/20 main is sort of useless for this app, will eventually change to add more functionallity like adding a menu with all options a user would need for a task app. 
public class Main {

    public static void main (String[]args){
        TaskList task1 = new TaskList();

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Task Name");
        String task = keyboard.nextLine();
        System.out.println("enter due date");
        String date = keyboard.nextLine();
        task1.addItem(task,date);


        System.out.println("Enter Task Name");
        String task2 = keyboard.nextLine();
        System.out.println("enter due date");
        String date2 = keyboard.nextLine();
        task1.addItem(task2,date2);

        task1.printList();
//        System.out.println("Enter Task Name");
//        String taskcom = keyboard.nextLine();
//        task1.taskCompleted(taskcom);
//        task1.printList();
    }
}

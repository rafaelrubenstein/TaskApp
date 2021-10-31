package com.example.todoapplication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    //     initializes a List
    List<Task> taskArrayList = new ArrayList<>();
    // allows user to add a new task object to the array list
    public void addItem(Task task){
        taskArrayList.add(task);
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
                Task t = new Task(tempTask,tempDate);
                taskArrayListIn.addItem(t);
                tempTask = taskStream.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("no file was read");
        }catch (IOException e){
            System.out.println("there was an error with the file");
        }
    }
    public boolean isEmpty(){
        return taskArrayList.size() ==0;
    }
    public int getSize(){
        return taskArrayList.size();
    }
    public Task getTaskObject(int index){
        if (index < 1 || index > getSize()){
            return null;
        }
        return taskArrayList.get(index-1);
    }
}
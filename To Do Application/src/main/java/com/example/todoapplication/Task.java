package com.example.todoapplication;

import java.util.Scanner;

public class Task {

    private String task;
    private String date;
    private boolean isDone;
    // constructor for Task object. user may only enter tasks that are less than 50 characters
    public Task (String taskIn, String dateIn){
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

    public void setTask(String taskIn){
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

package com.example.todoapplication;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ToDoGUI extends Application {
    //attributes
    private TaskList task1 = new TaskList();
    // WIDTH and HEIGHT of GUI stored as constants
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    //visual components
    private Label headingLabel = new Label("To Do");
    private Label taskLabel1 = new Label("Task");
    private TextField taskField =  new TextField();
    private Label taskLabel2 = new Label("Task");
    private TextField taskField2 =  new TextField();
    private Label dateLabel = new Label("Date");
    private TextField dateField =  new TextField();
    private Button addButton = new Button("Add Task");
    private Button displayButton =  new  Button("Display Tasks");
    private Button removeButton  = new Button("Remove Task");
    private Label dueDateLabel =new Label("New Due Date");
    private TextField dueDateField =new TextField();
    private Button taskCompleteButton  = new Button("Complete Task");
    private Label taskCompleteLabel =new Label("Task that was completed");
    private TextField taskCompletedField =new TextField();
    private Button changeDateButton =new Button("Change due date");
    private Button saveAndQuitButton  = new Button("Save and Quit");
    private TextArea displayArea1  = new TextArea();
    private TextArea displayArea2 = new TextArea();


    @Override
    public void start(Stage stage) throws IOException {
        TaskList.readList(task1);

        // create four HBoxes
        HBox taskDetails = new HBox (10);
        HBox taskButtons = new HBox(10);
        HBox dateDetails = new HBox(10);
        HBox dateButtons = new HBox(10);
        HBox saveButton = new HBox(10);

        taskDetails.getChildren().addAll(taskLabel1,taskField,dateLabel,dateField);
        taskButtons.getChildren().addAll(addButton,displayButton,removeButton);

        dateDetails.getChildren().addAll(taskLabel2,taskField2,dueDateLabel,dueDateField,taskCompleteLabel,taskCompletedField);
        dateButtons.getChildren().addAll(changeDateButton,taskCompleteButton);

        saveButton.getChildren().addAll(saveAndQuitButton);

        // create VBox
        VBox root = new VBox(10);

       root.getChildren().addAll(headingLabel,taskDetails,taskButtons,displayArea1,dateDetails,dateButtons,displayArea2,saveButton);

        // create the scene
        Scene scene = new Scene(root, Color.LIGHTBLUE);

        // set font of heading
        Font font = new Font("Calibri", 40);
        headingLabel.setFont(font);

        taskDetails.setAlignment(Pos.CENTER);
        taskButtons.setAlignment(Pos.CENTER);
        dateDetails.setAlignment(Pos.CENTER);
        dateButtons.setAlignment(Pos.CENTER);
        saveButton.setAlignment(Pos.CENTER);

        // set alignment of VBox
        root.setAlignment(Pos.CENTER);

        taskField.setMaxWidth(100);

        taskDetails.setMinWidth(WIDTH);
        taskDetails.setMaxWidth(WIDTH);

        taskButtons.setMinWidth(WIDTH);
        taskButtons.setMaxWidth(WIDTH);

        dateDetails.setMinWidth(WIDTH);
        dateDetails.setMaxWidth(WIDTH);

        dateButtons.setMinWidth(WIDTH);
        dateButtons.setMaxWidth(WIDTH);

        saveButton.setMinWidth(WIDTH);
        saveButton.setMaxWidth(WIDTH);


        root.setMinSize(WIDTH,HEIGHT);
        root.setMaxSize(WIDTH,HEIGHT);


        displayArea1.setMaxSize(WIDTH-50,HEIGHT-100);
        displayArea2.setMaxSize(WIDTH-100,HEIGHT-300);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        BorderStroke style = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                new CornerRadii(0), new BorderWidths(2) );

        root.setBorder(new Border (style));
        root.setBackground(Background.EMPTY);

        // customise buttons
        addButton.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,
                new CornerRadii(10), Insets.EMPTY)));
        displayButton.setBackground(	new Background(new BackgroundFill(Color.LIGHTYELLOW,
                new CornerRadii(10), Insets.EMPTY)));
        removeButton.setBackground(	new Background(new BackgroundFill(Color.LIGHTYELLOW,
                new CornerRadii(10), Insets.EMPTY)));
        saveAndQuitButton.setBackground(	new Background(new BackgroundFill(Color.LIGHTYELLOW,
                new CornerRadii(10), Insets.EMPTY)));
        changeDateButton.setBackground(	new Background(new BackgroundFill(Color.LIGHTYELLOW,
                new CornerRadii(10), Insets.EMPTY)));

        // call private methods for button event handlers
        addButton.setOnAction(e -> addTask());
        displayButton.setOnAction(e -> displayHandler() );
        removeButton.setOnAction( e -> removeHandler());
        changeDateButton.setOnAction( e -> changeDate());
        taskCompleteButton.setOnAction(e-> taskComplete());
        saveAndQuitButton.setOnAction( e -> saveAndQuitHandler());

        stage.setScene(scene);
        stage.setTitle("To Do");
        stage.setResizable(false); // see discussion below
        stage.show();

    }
    //    allows user to add a task
    private void addTask(){
        String task = taskField.getText();
        String date = dateField.getText();

        if(task.length() == 0 || date.length() ==0){
            displayArea1.setText("You must enter a task and due date");
        }else if (task1.getTaskInList(task)!=null){
            displayArea1.setText("This task is in the list already");
        }
        else {
            Task t = new Task(task,date);
            task1.addItem(t);
            taskField.setText("");
            dateField.setText("");
            displayArea1.setText(task + " with due date of "+ date + " was added");
            TaskList.writeList(task1);
        }
    }
    public void displayHandler()
    {
        int i;
        if(task1.isEmpty()) // no tasks to display
        {
            displayArea1.setText("There are no tasks due");
        }
        else // display tasks
        {
            displayArea1.setText("Task" +  "\t" +  "due date" +  "\n");
            for(i = 1; i <=  task1.getSize(); i++ )
            {
                displayArea1.appendText(task1.getTaskObject(i).getTask()
                        + "\t\t"
                        + task1.getTaskObject(i).getDate() + "\n");
            }
        }
    }
    private void removeHandler()
    {
        String task =  taskField.getText();
        // check for errors
        if(task.length()== 0)
        {
            displayArea1.setText("Task must be entered");
        }

        else if(task1.getTaskInList(task)== null)
        {
            displayArea1.setText("The task you entered isn't in the list");
        }
        else // ok to remove Task
        {
            task1.taskCompleted(task);
            displayArea1.setText(task + " was removed");
            TaskList.writeList(task1);
        }
    }
    //    allows user to change the due date of the task
    private void changeDate(){
        String task = taskField2.getText();
        String date= dueDateField.getText();
      if (task.length() == 0 || date.length() ==0){
          displayArea2.setText("The task and date must be entered");
      }else if (task1.getTaskInList(task)==null){
          displayArea2.setText("There is no such task");
      }else {
          task1.getTaskInList(task).setDate(date);
          displayArea2.setText(task + " date changed to "+ date);
      }
    }
    //    allows the user to set the task as complete, this will remove it from the list
    private void taskComplete(){
       String task = taskCompletedField.getText();
        if (task.length() == 0){
            displayArea2.setText("The task must be entered");
        }else if (task1.getTaskInList(task)==null){
            displayArea2.setText("There is no such task");
        }else {
            task1.taskCompleted(task);
            displayArea2.setText(task + " was completed");
            TaskList.writeList(task1);
        }
    }
    private void saveAndQuitHandler()
    {
        TaskList.writeList(task1);
        Platform.exit();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
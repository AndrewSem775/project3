//Andrew Semchishin
//Thursday, March 7

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Task> myTasks = new ArrayList<>();

    public static void main(String[] args) {

        try {
            deserialize();
            String choice = "";

            while (!(choice.equals("0"))) {
                System.out.println("\nPlease choose an option:\n" +
                        "(1) Add a task.\n" +
                        "(2) Remove a task.\n" +
                        "(3) Update a task.\n" +
                        "(4) List all tasks.\n" +
                        "(5) List all tasks by priority.\n" +
                        "(6) Special surprise\n" +
                        "(0) Exit.");
                choice = input.nextLine();

                if (choice.equals("1")) {
                    System.out.println("what task name you wonna add?");
                    String taskName = input.nextLine();
                    System.out.println(("give a description of the task"));
                    String taskDescription = input.nextLine();
                    System.out.println("give a priority for the task");
                    int priorityOfTask = input.nextInt();
                    input.nextLine();

                    Task newTask = new Task(taskName, taskDescription, priorityOfTask);
                    myTasks.add(newTask);

                } else if (choice.equals("2")) {
                    System.out.println("what task you wonna remove? (starting from 0)");
                    System.out.println(myTasks);
                    String removeTask = input.nextLine();
                    myTasks.remove(Integer.parseInt(removeTask));
                } else if (choice.equals("3")) {
                    System.out.println("what task you wonna update? (starting from 0)");
                    System.out.println(myTasks);
                    String removeTask = input.nextLine();
                    myTasks.remove(Integer.parseInt(removeTask));

                    System.out.println("What is the new task name?");
                    String taskName = input.nextLine();
                    System.out.println(("What is the new task description"));
                    String taskDescription = input.nextLine();
                    System.out.println("What is the new task priority (1-5)");
                    int priorityOfTask = input.nextInt();
                    input.nextLine();

                    Task newTask = new Task(taskName, taskDescription, priorityOfTask);
                    myTasks.add(newTask);

                } else if (choice.equals("4")) {
                    Collections.sort(myTasks);
                    for(Task a: myTasks){
                        System.out.println(a);
                    }


                } else if (choice.equals("5")) {
                    System.out.println("Which priority do you want to list? (1-5)");
                    int choice3 = input.nextInt();
                    input.nextLine();

                    for (int i = 0; i < myTasks.size(); i++) {
                        if (myTasks.get(i).getPriority() == choice3){
                            System.out.println(myTasks.get(i));
                        }
                    }


                } else if(choice.equals("6")){
                    System.out.println("dude killorAN");
                }


            }
            serialize();

        } catch (Exception e){
            System.out.println("something went wrong, please try again.");
        }
    }
    static void serialize(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("tasklist.json")){
            gson.toJson(myTasks,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void deserialize(){
        try (FileReader reader = new FileReader("tasklist.json")){
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>(){}.getType();
            myTasks = gson.fromJson(jsonElement,type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

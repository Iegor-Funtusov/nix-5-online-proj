package Controller;

import Task1.Task1UserInterface;
import Task2.Task2UserInterface;
import Task3.Task3UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskController {

    public static void userUI(){
        Task1UserInterface task1 = new Task1UserInterface();
        Task2UserInterface task2 = new Task2UserInterface();
        Task3UserInterface task3 = new Task3UserInterface();

        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            while (true){
                System.out.println("Choose the task: 1-parse date, 2-find unique element, 3-find shortest way, 'Q'-exit");
                switch (bf.readLine().toUpperCase()){
                    case "1" -> task1.task1UserUI();
                    case "2" -> task2.task2UserUI();
                    case "3" -> task3.task3UserUI();
                    case "Q" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}

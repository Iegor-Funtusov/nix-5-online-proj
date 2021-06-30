package Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Task3UserInterface {
    private final BufferedReader bf;

    public Task3UserInterface(){
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public void task3UserUI(){
        String AGREE_INPUT = "1";
        String choose;
        try {
            printTask3Condition();
            do{
                System.out.println("1-Run the solution, 2-see all info about cities, 'Q'-exit");
                choose = bf.readLine();

                switch (choose.toUpperCase()){
                    case "1" -> runSolution();
                    case "2" -> printAllInfo();
                    case "Q" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue working with this task? 1-yes, else-no");
                choose = bf.readLine();

            } while (choose.equals(AGREE_INPUT));

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    //Для проверки работоспособности можно выбрать путь Киев - Винница. Напрямую стоимость = 14
    //Но если ехать через Житомир за 6, а потом до Винницы за 5, то получим более короткий путь
    private void runSolution(){
        try {
            printAllInfo();
            List<City> cities = FileWork.readInfoFromFile();

            System.out.println("Enter index of city FROM which you want to find path:");
            int start = Integer.parseInt(bf.readLine());
            start--;                    //Т.к. для пользователя индксация начинается с единицы

            System.out.println("Enter index of city TO which of them you want to find path:");
            int end = Integer.parseInt(bf.readLine());
            end--;

            validateIndexes(cities.size(), start, end);

            int shortestWay;
            if(start == end){
                System.out.println("You have selected two identical cities");
                shortestWay = 0;
            } else {
                shortestWay = Solution.getSolution(cities, start, end);
            }

            String startCityName = cities.get(start).getCityName();
            String endCityName = cities.get(end).getCityName();
            System.out.println("Shortest way from " +  startCityName + " to " + endCityName + " is " + shortestWay + "\n");
            FileWork.writeInfoToFile(shortestWay);

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void printAllInfo(){
        List<City> cities = FileWork.readInfoFromFile();
        System.out.println("Info about cities from file:");
        System.out.println("neighbourValueMap: key - neighbour, value - cost of transit");
        for (int i = 0; i < cities.size(); i++) {
            System.out.println((i + 1) + ") " + cities.get(i));
        }
        System.out.println();
    }


    private void validateIndexes(int quantity, int start, int end){
        if(start < 0 || start > quantity - 1 || end < 0 || end > quantity - 1){
            throw new RuntimeException("Incorrect values entered. Try once more");
        }
    }


    private void printTask3Condition(){
        System.out.println("""
                Task 3:
                A list of cities is given. Each path between cities has a cost (positive integer). 
                The task is to find the most profitable route between the two cities. 
                The maximum possible cost of a path is 200,000.
                """);
    }

}

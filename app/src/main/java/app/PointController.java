package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PointController {
    private final PointService pointService;
    private final BufferedReader bf;


    PointController(){
        pointService = new PointService();
        bf = new BufferedReader(new InputStreamReader(System.in));
    }



    public void userInterface() throws IOException {
        do {
            System.out.println("Choose the crud operation: 1-create, 2-update, 3-read one point, 4-read all points, 5-delete");
            switch (Integer.parseInt(bf.readLine())){
                case 1 -> createPoint();
                case 2 -> updatePoint();
                case 3 -> readOnePoint();
                case 4 -> readAllPoints();
                case 5 -> deletePoint();
                default -> System.out.println("Incorrect value entered");
            }

            System.out.println("Do you want to continue? 1-yes, else-no");

        } while (Integer.parseInt(bf.readLine()) == 1);
    }



    private void createPoint() throws IOException {
        Point pointToAdd = new Point();

        System.out.println("Enter the name of a point");
        pointToAdd.setName(bf.readLine());

        System.out.println("Enter X coordinate of a point " + pointToAdd.getName());
        pointToAdd.setX(Integer.parseInt(bf.readLine()));

        System.out.println("Enter Y coordinate of a point " + pointToAdd.getName());
        pointToAdd.setY(Integer.parseInt(bf.readLine()));

        System.out.println("Enter Z coordinate of a point " + pointToAdd.getName());
        pointToAdd.setZ(Integer.parseInt(bf.readLine()));

        pointService.create(pointToAdd);
        System.out.println("Was successfully created: " + pointToAdd.toString());
    }



    private void updatePoint() throws IOException{
        if(pointService.read().size() == 0){
            System.out.println("There are no points");
            return;
        }

        System.out.println("Enter the name of the point which you want to update:");
        Point pointToUpd = pointService.isExist(bf.readLine());

        if(pointToUpd == null){
            System.out.println("Point with such name does not exist");
            return;
        }

        System.out.println("Enter new X coordinate of a point " + pointToUpd.getName());
        pointToUpd.setX(Integer.parseInt(bf.readLine()));

        System.out.println("Enter new Y coordinate of a point " + pointToUpd.getName());
        pointToUpd.setY(Integer.parseInt(bf.readLine()));

        System.out.println("Enter new Z coordinate of a point " + pointToUpd.getName());
        pointToUpd.setZ(Integer.parseInt(bf.readLine()));

        pointService.update(pointToUpd);
        System.out.println("Updated information about the point: " + pointToUpd.toString());
    }



    private void deletePoint() throws IOException {
        if(pointService.read().size() == 0){
            System.out.println("There are no points");
            return;
        }

        System.out.println("Enter the name of the point which you want to delete");
        Point pointToDelete = pointService.isExist(bf.readLine());
        if (pointToDelete == null){
            System.out.println("Point with such name does not exist");
            return;
        }

        pointService.delete(pointToDelete.getId());
        System.out.println("Successfully deleted. Do you want to see all your points? 1-yes, else-no");
        if(Integer.parseInt(bf.readLine()) == 1){
            readAllPoints();
        }
    }



    private void readOnePoint() throws IOException {
        if(pointService.read().size() == 0){
            System.out.println("There are no points");
            return;
        }

        System.out.println("Enter name of the point, info about which fo you want to know");
        Point pointToRead = pointService.isExist(bf.readLine());
        if (pointToRead == null){
            System.out.println("Point with such name does not exist");
            return;
        }
        System.out.println(pointToRead.toString());
    }



    private void readAllPoints(){
        if(pointService.read().size() == 0){
            System.out.println("There are no points");
            return;
        }

        System.out.println("All your points:");
        pointService.read().forEach(System.out :: println);
    }
}
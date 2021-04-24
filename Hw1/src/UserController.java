import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserController {
    private Db dataBase;

    UserController(){
       this.dataBase = new Db();
    }


    public void userInterface() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choose = new String();

        do {
            System.out.println("Please, choose what do you want to do?");
            System.out.println(" 1-add new figure \n 2-delete figure \n 3-get info about all figures \n 4-get info about the figure \n 5-get perimeter of all figures \n 6-get perimeter of one figure by id \n 7-get area of all figures \n 8-get area of one figure by id");
            choose = reader.readLine();

            switch (choose) {
                case "1": { this.createFigure(); } break;

                case "2": { this.deleteFigure(); } break;

                case "3": { this.getInfoAbAllFigures(); } break;

                case "4": { this.getInfoAbFigure(); } break;

                case "5": { this.calcAllPerimeters(); } break;

                case "6": { this.calcPerimeterOfFigure(); } break;

                case "7":{ this.calcAllAreas(); } break;

                case "8": { this.calcAreaOfFigure(); } break;

                default: { System.out.println("!Incorrect value entered!"); } break;
            }


            System.out.println("Do you want to continue?");
            choose = reader.readLine();
        } while (Integer.parseInt(choose) != 0);
    }


        private void createFigure () throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter what kind of figure do you want to crate? 1-Rectangle, 2-Circle");
            String choose = reader.readLine();

            switch (choose) {
                case "1": {
                    System.out.println("You chose to add a Rectangle, enter sides A and B:");
                    Rectangle rectangle = new Rectangle();
                    int a = Integer.parseInt(reader.readLine());
                    rectangle.setA(a);
                    int b = Integer.parseInt(reader.readLine());
                    rectangle.setB(b);
                    dataBase.addFigure(rectangle);
                    System.out.println("Rectangle was successfully created");
                    rectangle.printInfo();
                } break;

                case "2": {
                    System.out.println("You choose to add a Circle, enter Radius:");
                    Circle circle = new Circle();
                    int r = Integer.parseInt(reader.readLine());
                    circle.setRadius(r);
                    dataBase.addFigure(circle);
                    System.out.println("Circle was successfully created");
                    circle.printInfo();
                } break;

                default: { System.out.println("!Incorrect value entered!"); } break;
            }
        }

        private void deleteFigure() {
            System.out.println("Enter id of the figure which you want to delete:");
            int idOfFigureToDel = readID();
            dataBase.deleteFigure(idOfFigureToDel);
        }


        private void getInfoAbAllFigures(){
            ArrayList<Figure> figures = dataBase.getAllFigures();

            if(figures.size() == 0){
                System.out.println("There is no figures in dataBase");
                return;
            }

            System.out.println("Information about all figures: ");
            for (int i = 0; i < figures.size(); i++){
                System.out.println(figures.get(i).toString());
            }
        }


        private void getInfoAbFigure() {
            System.out.println("Enter id of the figure info about which do you want to get:");
            int requiredFigureID = readID();
            System.out.println(dataBase.getInfoAboutFigureByID(requiredFigureID));
        }



        private void calcAllPerimeters(){
            ArrayList<Figure> figures = dataBase.getAllFigures();

            if(figures.size() == 0){
                System.out.println("There is no figures in dataBase");
                return;
            }

            System.out.println("Perimeters of all figures: ");
            for (int i = 0; i < figures.size(); i++){
                System.out.println("Figure " + figures.get(i).getId() + " P = " + figures.get(i).calcPerimeter() + " cm");
            }
        }

        private void calcAllAreas(){
            ArrayList<Figure> figures = dataBase.getAllFigures();

            if(figures.size() == 0){
                System.out.println("There is no figures in dataBase");
                return;
            }

            System.out.println("Areas of all figures: ");
            for (int i = 0; i < figures.size(); i++){
                System.out.println("Figure " + figures.get(i).getId() + " P = " + figures.get(i).calcArea() + " cm^2");
            }
        }


        private void calcPerimeterOfFigure() {
            System.out.println("Enter id of the figure calculate perimeter of which do you want:");
            int requiredFigureID = readID();
            System.out.println(dataBase.getFigure(requiredFigureID).calcPerimeter() + "cm");
        }

        private void calcAreaOfFigure() {
            System.out.println("Enter id of the figure calculate area of which do you want:");
            int requiredFigureID = readID();
            System.out.println(dataBase.getFigure(requiredFigureID).calcArea() + "cm^2");
        }

        private int readID() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return Integer.parseInt(reader.readLine());
            }
            catch (IOException exception){
                exception.getStackTrace();
                return 0;
            }
        }
}
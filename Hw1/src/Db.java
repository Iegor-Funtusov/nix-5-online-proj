import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Db {
    private ArrayList<Figure> geometricFigures;


    public void userInterface() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("Please, choose what do you want to do?");
            System.out.println("1-add new figure, 2-delete figure, 3-get info about the figure, 4-get all figures, 5-get perimeter of all figures, 6-get area of all figures");
            String choose = reader.readLine();

            switch(choose){
                case "1" : {
                    createFigure();
                } break;

                case "2" : {
                    deleteFigure();
                } break;

                case "3" : {
                    System.out.println(getFigureById().toString());
                } break;

                case "4" : {
                    //Получить все фигуры
                } break;

                case "5" : {
                    //Периметр всех фигур
                } break;

                case "6" : {
                    //Площадь всех фигур
                } break;

                default : {
                    System.out.println("!Incorrect value entered!");
                } break;
            }


            System.out.println("Do you want to continue?");
            if(Integer.parseInt(reader.readLine()) != 1)
                break;
        }

    }



    public void createFigure() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter what kind of figure do you want to crate? 1-Rectangle, 2-Circle");
        String choose = reader.readLine();

        switch(choose){
            case "1" : {
                System.out.println("You chose to add a Rectangle, enter sides A and B:");
                Rectangle rectangle = new Rectangle();
                int a = Integer.parseInt(reader.readLine());
                rectangle.setA(a);
                int b = Integer.parseInt(reader.readLine());
                rectangle.setB(b);
                addFigure(rectangle);
                System.out.println("Rectangle was successfully created");
            }break;

            case "2" : {
                System.out.println("You choose to add a Circle, enter Radius:");
                Circle circle = new Circle();
                int r = Integer.parseInt(reader.readLine());
                circle.setRadius(r);
                addFigure(circle);
                System.out.println("Circle was successfully created");
            } break;

            default: {
                System.out.println("!Incorrect value entered!");
            } break;
        }
    }




    public void addFigure(Figure figureToAdd) {
        geometricFigures.add(figureToAdd);
    }

    public Figure getFigureById(){
        //Айди или считываю здесь или передаю через параметр
        //ищу фигуру по айди возвращаю её и в методе интерфейсе вывожу информацию о ней через переопределённый ту стринг
        return new Rectangle();
    }


    public void deleteFigure(){
        //Мб считать его здесь, или передавать как параметр
        String idOfFigureToDel;
        //Поиск фигуры по айди сначала
        //Если она есть удаляею, если нет - говорю что такой фигуры в базе данных нет

    }


    //Мб добавить ещё один метод getAllFiguresInfo() где возвращается ArrayList<String> в котором записанна информация от ту стринг по всем фигурам


    public ArrayList<Figure> getAllFigures(){
        return this.geometricFigures;
    }

    public ArrayList<Double> calcAllPerimeters(){
        //прохожу по всей базе фигур, вычисляю периметер всех фигур заношу их в массив, и возвращаю
        return new ArrayList<Double>();

    }

    public ArrayList<Double> calcAllAreas(){
        //прохожу по всей базе фигур, вычисляю площадь всех фигур заношу их в массив, и возвращаю
        return new ArrayList<Double>();
    }
}
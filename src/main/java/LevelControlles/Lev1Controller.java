package LevelControlles;

import Level1.KnightsMove;
import Level1.TriangleArea;
import Level1.UniqueElements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


public class Lev1Controller {
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));



    public void level1Interface() throws IOException {
        do {
            System.out.println("Which task do you want to check? 1-Unique elements, 2-Knight's move, 3-Area of the triangle");

            switch (Integer.parseInt(bf.readLine())) {
                case 1 -> task1Interface();
                case 2 -> task2Interface();
                case 3 -> task3Interface();
                default -> System.out.println("Incorrect value entered");
            }

            System.out.println("Do you want to continue at this level? 1-yes, else-no");

        } while (Integer.parseInt(bf.readLine()) == 1);
    }



    private void task1Interface() throws  IOException{
        System.out.println("You chose task 1 \"Unique elements in array\":");
        if (chooseMethodOfInput()) {
            int quantity;
            int[] nums;

            System.out.println("Enter quantity of the elements");
            quantity = Integer.parseInt(bf.readLine());
            if(quantity <= 0){
                System.out.println("Incorrect value entered");
                return;
            }

            nums = new int[quantity];
            for (int i = 0; i < quantity; i++) {
                System.out.println("Enter element №" + (i + 1));
                nums[i] = Integer.parseInt(bf.readLine());
            }

            UniqueElements uniqueElements = new UniqueElements(nums);

            System.out.println("Entered array:");
            for (int num : nums) {
                System.out.print(num + " ");
            }

            System.out.println("\nUnique elements in array: " + uniqueElements.uniqueSymbols());
        } else {
            UniqueElements uniqueElements = new UniqueElements();

            System.out.println("Entered array:");
            for (int num : uniqueElements.getNums()) {
                System.out.print(num + " ");
            }

            System.out.println("\nUnique elements in array: " + uniqueElements.uniqueSymbols());
        }
    }



    private void task2Interface() throws IOException{
        KnightsMove knight = null;
        System.out.println("You chose task 2 \"Knight`s move\":");

        do {
            System.out.println("1-create new Knight, 2-move");
            switch (Integer.parseInt(bf.readLine())){
                case 1 ->{
                    if(chooseMethodOfInput()){
                        knight = new KnightsMove();
                        System.out.println("Enter X coordinate of a figure:");
                        int xCoordinate = Integer.parseInt(bf.readLine());
                        knight.setX(xCoordinate);
                        System.out.println("Enter Y coordinate of a figure:");
                        int yCoordinate = Integer.parseInt(bf.readLine());
                        knight.setY(yCoordinate);
                    }
                    else{
                        knight = new KnightsMove();
                        knight.setX((int)(Math.random() * 50));
                        knight.setY((int)(Math.random() * 50));
                    }
                    System.out.println("Successfully created. " + knight.toString());

                }
                
                
                case 2 ->{
                    if(knight == null){
                        System.out.println("First you should to create Knight");
                        break;
                    }
                    System.out.println("You chose to move the Knight");
                    if(chooseMethodOfInput()){
                        System.out.println("");
                        System.out.println("Enter new X coordinate:");
                        int newX = Integer.parseInt(bf.readLine());
                        System.out.println("Enter new Y coordinate:");
                        int newY = Integer.parseInt(bf.readLine());
                        if(knight.checkPossibilityOfMove(newX, newY)){
                            System.out.println("Successfully moved");
                            System.out.println(knight.toString());
                        }
                        else{
                            System.out.println("Cannot move the figure to this coordinates");
                        }

                    }
                    else{
                        //Генерирую новые значения в близком диапазоне от текущего положения коня
                        int newX = knight.getX() + (int)(Math.random() * 3);
                        int newY = knight.getY() + (int)(Math.random() * 3);
                        System.out.println("Generated coordinates: x:" + newX + " y:" + newY);
                        if(knight.checkPossibilityOfMove(newX, newY)){
                            System.out.println("Successfully moved");
                            System.out.println(knight.toString());
                        }
                        else{
                            System.out.println("Cannot move the figure to this coordinates");
                        }
                    }

                }

                default -> System.out.println("Incorrect value entered");
            }
            System.out.println("Do you want to continue at this task? 1-yes, else-no");

        } while (Integer.parseInt(bf.readLine()) == 1);

    }



    private void task3Interface() throws IOException{
        String formattedArea;       //Для вывода только двух знаков после запятой

        System.out.println("You chose task 3 \"Calculating area of the triangle\":");
        if(chooseMethodOfInput()){
            System.out.println("Enter X coordinate for a point A");
            int xA = Integer.parseInt(bf.readLine());
            System.out.println("Enter Y coordinate for a point A");
            int yA = Integer.parseInt(bf.readLine());

            System.out.println("Enter X coordinate for a point B");
            int xB = Integer.parseInt(bf.readLine());
            System.out.println("Enter Y coordinate for a point B");
            int yB = Integer.parseInt(bf.readLine());

            System.out.println("Enter X coordinate for a point C");
            int xC = Integer.parseInt(bf.readLine());
            System.out.println("Enter Y coordinate for a point C");
            int yC = Integer.parseInt(bf.readLine());

            TriangleArea triangleArea = new TriangleArea(xA, yA, xB, yB, xC, yC);
            System.out.println("Entered points: " + triangleArea.toString());

            formattedArea = new DecimalFormat("#0.00").format(triangleArea.calculateArea());
            System.out.println("Area of the triangle: " + formattedArea + " cm^2");
        }
        else {
            TriangleArea triangleArea = new TriangleArea();
            System.out.println("Points: " + triangleArea.toString());

            formattedArea = new DecimalFormat("#0.00").format(triangleArea.calculateArea());
            System.out.println("Area of the triangle: " + formattedArea + " cm^2");
        }
    }



    private boolean chooseMethodOfInput() throws IOException{
        System.out.println("Choose method of input: 1-from the keyboard, else-mechanically");
        if(Integer.parseInt(bf.readLine()) == 1){
            return true;
        }
        return false;
    }

}

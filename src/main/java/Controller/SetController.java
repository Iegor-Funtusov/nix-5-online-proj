package Controller;
import Service.SetService;
import Set.MathSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class SetController {
    private final SetService service;
    private final BufferedReader bf;
    private final String AGREE_INPUT = "1";


    public SetController(){
        service = SetService.getInstance();
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public void userInterface(){
        while(true){
            try{
                userInterfaceAvailableAPI();
                String choose = bf.readLine();

                switch (choose){
                    case "1" -> createNewCurrentSet();
                    case "2" -> addElement();
                    case "3" -> getAllSet();
                    case "4" -> mainFunctionalityInterface();
                    case "5" -> clearSet();
                    case "0" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

            } catch (NumberFormatException | IOException e){
                System.out.println("Incorrect value entered");
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }




    private void createNewCurrentSet(){
        try {
            ArrayList<Number> elements = scanElements();
            Number []setElements = new Number[elements.size()];
            service.createSet(true, elements.toArray(setElements));
            showResult();

        } catch (NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (IOException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private ArrayList<Number> scanElements() throws IOException{
        ArrayList<Number> elements = new ArrayList<>();
        int index = 0;
        try{
            System.out.println("Enter the elements which you want to add by the 'Enter'");
            System.out.println("If you want to stop input elements you should press 'S'");
            do{
                System.out.println("Enter element #" + index + ":");
                String item = bf.readLine();
                if(item.equalsIgnoreCase("S")){
                    return  elements;
                }
                Number numToAdd = readElement(item);
                elements.add(numToAdd);
                index++;

            } while(true);

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } finally {
            return elements;
        }
    }



    private void addElement(){
        try {
            System.out.println("Enter the element which you want to add to the set:");
            String item = bf.readLine();
            Number numToAdd = readElement(item);
            service.addElement(numToAdd);
            System.out.println("Successfully added");
            showResult();
        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }



    private Number readElement(String numStr){
        try{
            return Byte.parseByte(numStr);

        } catch(NumberFormatException e){
            return readShort(numStr);
        }
    }


    private Number readShort(String numStr){
        try{
            return Short.parseShort(numStr);

        } catch(NumberFormatException e){
            return readInteger(numStr);
        }
    }


    private Number readInteger(String numStr){
        try{
            return Integer.parseInt(numStr);

        } catch(NumberFormatException e){
            return readLong(numStr);
        }
    }


    private Number readLong(String numStr){
        try{
            return Long.parseLong(numStr);

        } catch(NumberFormatException e){
            return readFloat(numStr);
        }
    }


    private Number readFloat(String numStr){
        try{
            return Float.parseFloat(numStr);

        } catch(NumberFormatException e){
            return readDouble(numStr);
        }
    }


    private Number readDouble(String numStr){
        try{
            return Double.parseDouble(numStr);

        } catch(NumberFormatException e){
            throw new RuntimeException("Incorrect value entered");
        }
    }


    private void getAllSet(){
        try {
            Number []elements = service.toArray();
            for(Number number : elements){
                System.out.print(number + "  ");
            }
            System.out.println();

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void mainFunctionalityInterface(){
        try{
            do{
                System.out.println("Choose the operation:");
                System.out.println("""
                        1-join sets\s
                        2-ascending sorting\s
                        3-descending sorting\s
                        4-get maximum\s
                        5-get minimum\s
                        6-get average\s
                        7-get median\s
                        8-squash\s
                        9-set to arr\s
                        10-get element by index\s
                        11-get size\s 
                        0-exit program""");

                switch (bf.readLine()){
                    case "1" -> joinSets();
                    case "2" -> ascSetSort();
                    case "3" -> descSetSort();
                    case "4" -> getMaxElement();
                    case "5" -> getMinElement();
                    case "6" -> getAverage();
                    case "7" -> getMedian();
                    case "8" -> squash();
                    case "9" -> setToArray();
                    case "10" -> getElementByIndex();
                    case "11" -> getSizeOfSet();
                    case "0" -> System.exit(1);
                    default -> System.out.println("Incorrect value entered");
                }

                System.out.println("Do you want to continue work with main functionality methods? 1-yes, else-no");
            } while (bf.readLine().equals("1"));

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void joinSets(){
        try{
            System.out.println("Create new set to join with yours");
            ArrayList<Number> elements = scanElements();
            Number []setElements = new Number[elements.size()];

            MathSet newSet = service.createSet(false, elements.toArray(setElements));
            service.join(newSet);

            System.out.println("Successfully joined");
            showResult();

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void ascSetSort() throws IOException{
        try {
            System.out.println("1-sort all set, 2-sort from index to index, 3-sort from num to end");
            switch (bf.readLine()){
                case "1" -> service.sortAsc();

                case "2" -> {
                    System.out.println("Do you want to see range of available indexes? 1-yes, else-no");
                    if(bf.readLine().equals(AGREE_INPUT)){
                        getAvailableIndexes();
                    }
                    System.out.println("Enter first index:");
                    int firstIndex = Integer.parseInt(bf.readLine());
                    System.out.println("Enter last index:");
                    int lastIndex = Integer.parseInt(bf.readLine());
                    service.sortAsc(firstIndex, lastIndex);
                }

                case "3" -> {
                    System.out.println("Enter number from which you want to sort the set:");
                    String item = bf.readLine();
                    Number from = readElement(item);
                    service.sortAsc(from);
                }

                default -> {
                    System.out.println("Incorrect value entered");
                    return;
                }
            }

            showResult();

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void descSetSort() throws IOException{
        try {
            System.out.println("1-sort all set, 2-sort from index to index, 3-sort from num to end");
            switch (bf.readLine()){
                case "1" -> service.sortDesc();

                case "2" -> {
                    System.out.println("Do you want to see range of available indexes? 1-yes, else-no");
                    if(bf.readLine().equals(AGREE_INPUT)){
                        getAvailableIndexes();
                    }
                    System.out.println("Enter first index:");
                    int firstIndex = Integer.parseInt(bf.readLine());
                    System.out.println("Enter last index:");
                    int lastIndex = Integer.parseInt(bf.readLine());
                    service.sortDesc(firstIndex, lastIndex);
                }

                case "3" -> {
                    System.out.println("Enter number from which you want to sort the set:");
                    String item = bf.readLine();
                    Number from = readElement(item);
                    service.sortDesc(from);
                }

                default -> {
                    System.out.println("Incorrect value entered");
                    return;
                }
            }

            showResult();

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void getMaxElement(){
        try{
            Number maxValue = service.getMax();
            getAllSet();
            System.out.println("Max element in set is: " + maxValue);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void getMinElement(){
        try {
            Number minValue = service.getMin();
            getAllSet();
            System.out.println("Min element in set is: " + minValue);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void getAverage(){
        try {
            DecimalFormat f = new DecimalFormat("##.00");
            Number average = service.getAverage();
            getAllSet();
            System.out.println("Average of set is: " + f.format(average));

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void getMedian(){
        try {
            DecimalFormat f = new DecimalFormat("##.00");
            Number median = service.getMedian();
            getAllSet();
            System.out.println("Median of set is: " + f.format(median));

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void squash(){
        try {
            System.out.println("Select the indices from which to which you want to get the set");
            System.out.println("Do you want to see range of available indexes? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                getAvailableIndexes();
            }

            System.out.println("Enter first index:");
            int firstIndex = Integer.parseInt(bf.readLine());
            System.out.println("Enter last index:");
            int lastIndex = Integer.parseInt(bf.readLine());

            MathSet newSet = service.squash(firstIndex, lastIndex);
            System.out.println("Successfully");
            System.out.println(Arrays.toString(newSet.toArray()));

            System.out.println("Do you want to make the resulting set current for next work? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                service.createSet(true, newSet);
            }

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void setToArray(){
        try {
            Number[] elements;
            System.out.println("1-get all set, 2-get from index to index");
            switch(bf.readLine()){
                case "1" -> {
                    elements = service.toArray();
                    for(Number item : elements){
                        System.out.print(item + " ");
                    }
                    System.out.println();
                }

                case "2" -> {
                    System.out.println("Do you want to see range of available indexes? 1-yes, else-no");
                    if(bf.readLine().equals(AGREE_INPUT)){
                        getAvailableIndexes();
                    }

                    System.out.println("Enter first index:");
                    int firstIndex = Integer.parseInt(bf.readLine());

                    System.out.println("Enter last index:");
                    int lastIndex = Integer.parseInt(bf.readLine());

                    elements = service.toArray(firstIndex, lastIndex);
                    for(Number item : elements){
                        System.out.print(item + " ");
                    }
                    System.out.println();
                }

                default -> System.out.println("Incorrect value entered");
            }

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void getElementByIndex(){
        try {
            System.out.println("Do you want to see range of available indexes? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                getAvailableIndexes();
            }

            System.out.println("Enter index of element");
            int indexOfElement = Integer.parseInt(bf.readLine());
            System.out.println("Element by index " + indexOfElement + " is: " + service.get(indexOfElement));

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void getSizeOfSet(){
        try {
            int size = service.getSize();
            System.out.println("Size of the set is: " + size + " elements");

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void clearSet(){
        try{
            System.out.println("1-clear all set, 2-clear few numbers");
            switch (bf.readLine()){
                case "1" -> {
                    service.clear();
                    System.out.println("Successfully cleared");
                }

                case "2" -> {
                    System.out.println("Enter elements which you want to delete:");
                    System.out.println("If you enter an element that is not in the set, it will be skipped");
                    ArrayList<Number> numbersToClear = scanElements();
                    Number []elements = new Number[numbersToClear.size()];
                    service.clear(numbersToClear.toArray(elements));
                    System.out.println("Successfully cleared");
                    showResult();
                }

                default -> System.out.println("Incorrect value entered");
            }

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }


    //Служебные методы
    private void getAvailableIndexes(){
        try {
            int sizeOfSet = service.getSize();
            switch (sizeOfSet){
                case 0 -> throw new RuntimeException("Set is empty");
                case 1 -> System.out.println("Available index is: 0");
                default -> System.out.println("Available indexes: 0-" + (sizeOfSet-1));
            }

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void showResult(){
        try {
            System.out.println("Do you want to see the result set? 1-yes, else-no");
            if(bf.readLine().equals(AGREE_INPUT)){
                getAllSet();
            }

        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }


    private void userInterfaceAvailableAPI(){
        System.out.println("Choose the operation: \n" +
                "1-create new set, " +
                "2-add element, " +
                "3-get all set, " +
                "4-test main functionality, " +
                "5-clear set, " +
                "0-exit program");
    }
}
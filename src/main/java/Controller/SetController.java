package Controller;

import Service.SetService;
import SetPackage.MathSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SetController {
    private final SetService service;
    private final BufferedReader bf;
    private final String AGREE_INPUT = "1";


    public SetController(){
        //service = SetService.getInstance();
        service = new SetService();
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

            } catch (NumberFormatException e){
                System.out.println("Incorrect value entered");
            }  catch (IOException e){
                System.out.println("Incorrect value entered");
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
//        finally {
//            try {
//                bf.close();
//            } catch (IOException exception) {
//                System.out.println(exception.getMessage());
//            }
//        }

    }




    private void createNewCurrentSet() throws IOException{
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
        String choose;
        int typeOfElement;

        try{
            typeOfElement = chooseTypeOfElement();
            do{
                switch (typeOfElement){
                    case 1 -> elements.add(scanInteger());
                    case 2 -> elements.add(scanDouble());
                    case 3 -> elements.add(scanFloat());
                    case 4 -> elements.add(scanByte());
                    case 5 -> elements.add(scanShort());
                    case 6 -> elements.add(scanLong());
                }

                System.out.println("Do you want to add one more element? 1-yes with same type, 2-yes with new type, else-no");
                choose = bf.readLine();
                if(choose.equals("2")){
                    typeOfElement = chooseTypeOfElement();
                }
            } while ( choose.equals("1") || choose.equals("2"));
        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered");
        }
        finally {
            return elements;
        }
    }


    private int chooseTypeOfElement(){
        try{
            System.out.println("Choose type of the element: 1-Integer, 2-Double, 3-Float, 4-Byte, 5-Short, 6-Long");
            System.out.println("If you entered incorrect value, by default will be chosen Integer type");
            int typeOfElement = Integer.parseInt(bf.readLine());
            if(typeOfElement < 1 || typeOfElement > 6){
                return  1;
            }
            return typeOfElement;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Incorrect value entered");
            return 1;
        }
    }


    private void addElement() throws IOException{
        try {
            int typeOfElement = chooseTypeOfElement();

            switch (typeOfElement){
                case 1 -> service.addElement(scanInteger());
                case 2 -> service.addElement(scanDouble());
                case 3 -> service.addElement(scanFloat());
                case 4 -> service.addElement(scanByte());
                case 5 -> service.addElement(scanShort());
                case 6 -> service.addElement(scanLong());
            }

            showResult();
        } catch (IOException | NumberFormatException e){
            System.out.println("Incorrect value entered. " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }


    private Integer scanInteger() throws IOException{
        System.out.println("Enter new integer element:");
        return Integer.parseInt(bf.readLine());
    }


    private Double scanDouble() throws IOException {
        System.out.println("Enter new double element:");
        return Double.parseDouble(bf.readLine());
    }


    private Float scanFloat() throws IOException {
        System.out.println("Enter new float element:");
        return Float.parseFloat(bf.readLine());
    }


    private Byte scanByte() throws IOException {
        System.out.println("Enter new byte element:");
        return Byte.parseByte(bf.readLine());
    }


    private Short scanShort() throws IOException {
        System.out.println("Enter new short element:");
        return Short.parseShort(bf.readLine());
    }


    private Long scanLong() throws IOException {
        System.out.println("Enter new long element:");
        return Long.parseLong(bf.readLine());
    }


    private void getAllSet(){
        Number []elements = service.toArray();
        for(Number number : elements){
            System.out.print(number + "  ");
        }
        System.out.println();
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


    private void joinSets()  throws IOException{
        System.out.println("Create new set to join with yours");
        ArrayList<Number> elements = scanElements();
        Number []setElements = new Number[elements.size()];
        MathSet newSet = service.createSet(false, elements.toArray(setElements));
        service.join(newSet);
        System.out.println("Successfully joined");
        showResult();
    }


    private void ascSetSort() throws IOException{
        System.out.println("1-sort all set, 2-sort from index to index, 3-sort from num to end");
        switch (bf.readLine()){
            case "1" -> {
                service.sortAsc();
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
                service.sortAsc(firstIndex, lastIndex);
            }
            case "3" -> {
                //Считать Number
            }
            default -> {
                System.out.println("Incorrect value entered");
                return;
            }
        }

        showResult();
    }


    private void descSetSort() throws IOException{
        System.out.println("1-sort all set, 2-sort from index to index, 3-sort from num to end");
        switch (bf.readLine()){
            case "1" -> {
                service.sortDesc();
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
                service.sortDesc(firstIndex, lastIndex);
            }
            case "3" -> {
                //Считать Number
            }
            default -> {
                System.out.println("Incorrect value entered");
                return;
            }
        }

        showResult();
    }


    private void getMaxElement(){
        Number maxValue = service.getMax();
        getAllSet();
        System.out.println("Max element in set is: " + maxValue);
    }


    private void getMinElement(){
        Number minValue = service.getMin();
        getAllSet();
        System.out.println("Min element in set is: " + minValue);
    }


    private void getAverage(){
        DecimalFormat f = new DecimalFormat("##.00");
        Number average = service.getAverage();
        getAllSet();
        System.out.println("Average of set is: " + f.format(average));
    }


    private void getMedian(){
        DecimalFormat f = new DecimalFormat("##.00");
        Number median = service.getMedian();
        getAllSet();
        System.out.println("Median of set is: " + f.format(median));
    }


    private void squash(){
        //Доделать
    }


    private void setToArray() throws IOException{
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
            default -> {
                System.out.println("Incorrect value entered");
                return;
            }
        }
    }


    private void getElementByIndex() throws IOException{
        System.out.println("Do you want to see range of available indexes? 1-yes, else-no");
        if(bf.readLine().equals(AGREE_INPUT)){
            getAvailableIndexes();
        }
        System.out.println("Enter index of element");
        int indexOfElement = Integer.parseInt(bf.readLine());
        System.out.println("Element by index " + indexOfElement + " is: " + service.get(indexOfElement));
    }


    private void getSizeOfSet(){
        int size = service.getSize();
        System.out.println("Size of the set is: " + size + " elements");
    }


    private void clearSet() throws IOException{
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
            default -> {
                System.out.println("Incorrect value entered");
                return;
            }
        }
    }


    //Служебные методы
    private void getAvailableIndexes(){
        int sizeOfSet = service.getSize();
        System.out.println("Available indexes: 0-" + (sizeOfSet-1));
    }


    private void showResult() throws  IOException{
        System.out.println("Do you want to see the result set? 1-yes, else-no");
        if(bf.readLine().equals(AGREE_INPUT)){
            getAllSet();
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
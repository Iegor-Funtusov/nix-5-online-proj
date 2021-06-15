package Vaidation;

import SetPackage.MathSet;
import SetPackage.Node;

public class Validation {

    public static void validateIndex(MathSet set, int index){
        if(set == null){
            throw new RuntimeException("Set does not exist");
        }
        if(index < 0 || index > (set.size()-1)){
            throw new RuntimeException("Index out of bounds");
        }
    }


    public static void validateIndex(MathSet set, int firstIndex, int lastIndex){
        if(set == null){
            throw new RuntimeException("Set does not exist");
        }
        if(lastIndex < firstIndex || firstIndex < 0 || lastIndex > (set.size() - 1)){
            throw new RuntimeException("Incorrect indexes");
        }
    }


    public static void isEmptySet(MathSet setToCheck){
        if(setToCheck == null){
            throw new RuntimeException("Set does not exist");
        }
        if(setToCheck.size() == 0 || setToCheck.getTop() == null){
            throw new RuntimeException("Set is empty");
        }
    }


    public static void isEmptySet(MathSet ... setsToCheck){
        if(setsToCheck == null){
            throw new RuntimeException("Sets do not exist");
        }
        for(MathSet item : setsToCheck){
            isEmptySet(item);
        }
    }


    public static void isEmptyElement(Number numberToCheck){
        if(numberToCheck == null){
            throw new RuntimeException("Number does not exist");
        }
    }

    public static void isEmptyElement(Number ... numbersToCheck){
        if(numbersToCheck == null){
            throw new RuntimeException("Numbers do not exist");
        }
        for(Number item : numbersToCheck){
            isEmptyElement(item);
        }
    }


    public static void isEmptyArray(Number[] arrayToCheck){
        if(arrayToCheck == null){
            throw new RuntimeException("Array does not exist");
        }
        for(Number item : arrayToCheck){
            isEmptyElement(item);
        }
    }


    public static void isEmptyArray(Number[] ... arraysToCheck){
        if(arraysToCheck == null){
            throw new RuntimeException("Arrays do not exist");
        }
        for(Number[] array : arraysToCheck){
            isEmptyArray(array);
        }
    }


    public static void isContainElement(MathSet set, Number element){
        isEmptySet(set);
        isEmptyElement(element);
        Node localTop = set.getTop();
        while(localTop != null){
            if(localTop.info.equals(element)){
                return;
            }
            localTop = localTop.next;
        }

        throw new RuntimeException("Such element does not exist in set");
    }



    public static void isContainElement(MathSet set, Number[] elements){
        isEmptySet(set);
        isEmptyArray(elements);
        for(Number item : elements){
            isContainElement(set, item);
        }
    }

}
package ua.com.nkrasnovoronka.tasks.level1.task1;

public class UniqueArrayElement {
    public static int getNumberOfUniqueElements(int[] array) {
        int count = 0;
        boolean seen = false;
        for(int i = 0; i < array.length; i++){
            for (int j = 0; j < i ; j++) {
                if (array[i] == array[j]) {
                    seen = true;
                    break;
                }
            }
            if(!seen){
                count++;
            }
            seen = false;
        }
        return count;
    }
}

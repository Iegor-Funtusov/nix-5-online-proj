package ua.com.module1;

import java.util.Arrays;

public class DetermineNumberUniqueSymbols {
    public static int getUniqueNumber(int [] array){
        int count = 1;
        Arrays.sort(array);
        for(int i = 0; i < array.length-1; i++){
            if(array[i]!=array[i+1]){
                count++;
            }
        }
        return count;
    }
}

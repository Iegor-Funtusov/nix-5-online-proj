package util;

public class Part3 {

    public void quantityElementsBiggerPrevious(int [] args){
        System.out.print("Quantity of elements that bigger than previous: ");
        int quantity = 0;
        for(int i=0; i<args.length; i++){
            if(i!=0 && args[i]>args[i-1]){
                quantity++;
            }
        }
        System.out.println(" " + quantity);
    }
}

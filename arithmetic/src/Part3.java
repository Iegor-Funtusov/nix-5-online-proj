public class Part3 {

    public void quantityElementsBiggerPrevious(int [] args){
        int quantity = 0;
        for(int i=0; i<args.length; i++){
            if(i!=0 && args[i]>args[i-1]){
                quantity++;
            }
        }
        System.out.println(quantity);
    }
}

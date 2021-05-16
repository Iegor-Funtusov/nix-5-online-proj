package Level1;

public class UniqueElements {
    private int []nums;

    public UniqueElements(){
        this.nums = new int[(int)(Math.random() * 20)];
        for(int i = 0; i < nums.length; i++){
            nums[i] = (int)(Math.random() * 100);
        }
    }

    public UniqueElements(int []numbers){
        this.nums = numbers.clone();
    }


    public int uniqueSymbols(){
        int quantity = 0;
        boolean flag;

        for(int i = 0 ; i < nums.length ; i++) {
            flag = false;
            for(int j = i+1 ; j < nums.length ; j++) {
                if(nums[i] == nums[j]) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                quantity++;
        }


        return quantity;
    }

    public int[] getNums() {
        return nums;
    }
}

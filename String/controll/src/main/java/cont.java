public class cont {

        public static void main(String[] args) {

            String str = "Revers";
            String subS = "ver";
            CharSequence fir = "r";
            CharSequence last = "s";


            System.out.println(ReverseString.toReverse(str));
            System.out.println(ReverseString.ReverseBySubStr(str, subS));
            System.out.println(ReverseString.ReverseByIndex(str,0,2));
            System.out.println(ReverseString.ReverseBySymbol(str,'e','v'));
            System.out.println(ReverseString.ReverseByCharSequence(str, fir,last));
        }


}

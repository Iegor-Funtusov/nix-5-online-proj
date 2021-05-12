import com.nixsolutions.Reverser;

public class MainApp {
    public static void main(String[] args) {

        String str = "string";
        String subStr = "ing";
        CharSequence firstCharSequence = "s";
        CharSequence lastCharSequence = "r";

        // gnirts
        System.out.println(Reverser.toReverse(str));

        // strgni
        System.out.println(Reverser.toReverseBySubStr(str, subStr));

        // rtsing
        System.out.println(Reverser.toReverseByIndex(str,0,2));

        // sirtng
        System.out.println(Reverser.toReverseBySymbol(str,'t','i'));

        // rtsing
        System.out.println(Reverser.toReverseByCharSequence(str,firstCharSequence,lastCharSequence));
    }
}

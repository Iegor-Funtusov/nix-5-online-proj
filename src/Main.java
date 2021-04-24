import org.apache.commons.lang3.*;
import org.apache.commons.collections.*;

public class Main {

    public static void main(String[] args){

        System.out.println("Main file compiled");
        String s = "aaaa";
        s = StringUtils.upperCase(s);
        First.main(args);
        Second.main(args);
        Third.main(args);
    }

}
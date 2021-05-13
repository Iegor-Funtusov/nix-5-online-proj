import com.nixsolutions.courses.level1.Level1;
import com.nixsolutions.courses.level2.Level2;
import com.nixsolutions.courses.level3.Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModuleMain {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        String input;
        System.out.println("-----------------------\n     module_1\n-----------------------");
        try {
            while (true) {
                System.out.println("Choose level:\n0 - exit\n1 - level 1\n2 - level 2\n3 - level 3");
                input = reader.readLine();

                switch(input) {
                    case "0": System.exit(0);
                    case "1":
                        Level1.run();
                        break;
                    case "2":
                        Level2.run();
                        break;
                    case "3":
                        Level3.run();
                        break;
                }
            }
        } catch(IOException e) {
            System.out.println("Something went wrong");
        }
    }

}

package ua.com.nkrasnovoronka.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static List<String> readFile(String filePath) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.ready()){
                strings.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void writeToFile(List<String> input, String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for(String s: input){
                bw.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

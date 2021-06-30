package tasks.graphs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFileController {

    public void output(List<String> input, int[] results, String path){
        int index = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append("Distances between cities:\n");
            for(String in : input) {
                String[] distance = in.split(" ");
                if (Character.isLetter(distance[0].charAt(0)) && distance.length == 2) {
                    if(results[index] == -1){
                        String string = distance[0] + "-->" + distance[1] + "=" + "No directions" + "\n";
                        writer.append(string);
                        index++;
                        continue;
                    }
                    String string = distance[0] + "-->" + distance[1] + "=" + results[index] + "\n";
                    writer.append(string);
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

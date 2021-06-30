package Task3;

import Configs.FilesPaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWork {
    public static List<City> readInfoFromFile() {
        try {
            File file = new File(FilesPaths.TASK_3_INPUT.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<City> cities = new ArrayList<>();
            City city = new City();

            String line = reader.readLine();
            city.setName(line);
            while (line != null) {
                if(line.matches(".*\\d+.*")){
                    String []data = line.split(" ");
                    Integer neighbour = Integer.parseInt(data[0]);
                    Integer value = Integer.parseInt(data[1]);
                    city.addNewNeighbourValue(neighbour, value);
                }
                else{
                    if(!city.getNeighbourValueMap().isEmpty()){
                        cities.add(city);
                        city = new City();
                        city.setName(line);
                    }
                }
                line = reader.readLine();
            }
            cities.add(city);
            return cities;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void writeInfoToFile(int data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FilesPaths.TASK_3_OUTPUT.getPath()))){
            writer.write(data + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

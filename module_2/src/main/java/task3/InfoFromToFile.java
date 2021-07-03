package task3;

import java.io.*;

public class InfoFromToFile {
    int [] result;
    ShortestPath shortestPath;

    public void getInfoFromFile(String path) {
        try (BufferedReader scanner = new BufferedReader(new FileReader(path))) {
        int citiesCount = Integer.parseInt(scanner.readLine());
        int [][] graph = new int [citiesCount][citiesCount];
        String [] citiesNames = new String[citiesCount];
        for(int i = 0; i < citiesCount; i++){
            String city = scanner.readLine();
            citiesNames[i] = city;
            int countNodes = Integer.parseInt(scanner.readLine());
            for(int j = 0; j < countNodes; j++) {
                String s = scanner.readLine();
                String[] divider = s.split("\\s+");
                graph[i][Integer.parseInt(divider[0]) - 1] = Integer.parseInt(divider[1]);
            }
        }
        shortestPath = new ShortestPath();
        shortestPath.setCities(citiesCount);
        shortestPath.setGraph(graph);
        shortestPath.setCitiesNames(citiesNames);
        int searchPathCount = Integer.parseInt(scanner.readLine());
        if(searchPathCount <=100) {
            result = new int[searchPathCount];
        }
        else {
            throw new IllegalArgumentException("Paths` count should be less tnan 100");
        }
        for(int k = 0; k < result.length; k++){
            String task = scanner.readLine();
            String[] divider = task.split("\\s+");
            result[k] = shortestPath.dijkstra(shortestPath.getCityIndex(divider[0]),
                    shortestPath.getCityIndex(divider[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfoToFile(String path){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))){
            for (int value : result) {
                writer.write(value + "\n");
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package tasks.graphs;

import java.util.HashMap;
import java.util.List;

public class CountDistance {
    public int[] distance(List<String> input){
        HashMap<String, Integer> cities = defineCities(input);
        int[][] convertToMatrix = defineDistance(input, cities.size());
        int[][] distance = new int[cities.size()][cities.size()];

        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (convertToMatrix[i][j] == -1)
                    distance[i][j] = 1000;
                else
                    distance[i][j] = convertToMatrix[i][j];
            }
        }

        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                for (int k = 0; k < cities.size(); k++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        return getResult(input, distance, cities);
    }

    private HashMap<String, Integer> defineCities(List<String> input){
        HashMap<String, Integer> map = new HashMap<>();
        int index = 1;
        for (String in : input) {
            String[] values = in.split(" ");
            if(Character.isLetter(in.charAt(0)) && values.length == 1){
                map.put(in, index);
                index++;
            }
        }
        return  map;
    }

    private int[][] defineDistance(List<String> input, int size){
        int[][] distance = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distance[i][j] = -1;
                if(i == j)
                    distance[i][j] = 0;
            }
        }

        int index = -1;
        for(String in : input){
            String[] values = in.split(" ");
            if(Character.isDigit(in.charAt(0)) && values.length == 2) {
                for (int i = 0; i < size; i++) {
                    if(Integer.parseInt(values[0])-1 == i && distance[index][i] == -1)
                        distance[index][i] = Integer.parseInt(values[1]);
                }
            }
            if(Character.isLetter(in.charAt(0)) && values.length == 1){
                index++;
            }
        }
        return distance;
    }

    private int[] getResult(List<String> input, int[][] distances, HashMap<String, Integer> cities){
        int[] rows = new int[100];
        int counter = 0;

        for(String in : input){
            String[] distance = in.split(" ");
            if(Character.isLetter(distance[0].charAt(0)) && distance.length == 2){
                int firstCityIndex = cities.get(distance[0]);
                int secondCityIndex = cities.get(distance[1]);
                if(distances[firstCityIndex-1][secondCityIndex-1] == 1000)
                    rows[counter] = -1;
                else
                    rows[counter] = distances[firstCityIndex-1][secondCityIndex-1];
                counter++;
            }
        }

        int[] result = new int[counter];
        for (int i = 0; i < counter; i++) {
            result[i] = rows[i];
        }
        return result;
    }
}

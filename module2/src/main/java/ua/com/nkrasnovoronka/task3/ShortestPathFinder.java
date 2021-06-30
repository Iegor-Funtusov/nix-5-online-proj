package ua.com.nkrasnovoronka.task3;

import ua.com.nkrasnovoronka.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPathFinder {
    public void parseFile(String filePath) {
        List<String> strings = FileUtil.readFile(filePath);
        int numberOfCities = Integer.parseInt(strings.get(0));

        List<String> cities = getListOfCities(strings, numberOfCities);
        int[][] matrix = createMatrixOfCities(strings, numberOfCities);

        List<String> destinationSites = new ArrayList<>();
        getDestinationCities(strings, numberOfCities);
        System.out.println("cities = " + cities);
        System.out.println(Arrays.deepToString(matrix));

    }


    private List<String> getListOfCities(List<String> file, int numberOfCities) {
        List<String> cities = new ArrayList<>();
        while (cities.size() != numberOfCities + 1) {
            for (String s : file) {
                if (s.matches("^[a-zA-Z]+$")) {
                    cities.add(s);
                }
            }
        }
        return cities;
    }

    private int[][] createMatrixOfCities(List<String> file, int numberOfCities) {
        int[][] matrix = new int[numberOfCities][numberOfCities];
        int tmp = 0;
        for (int i = 1; i < file.size(); i++) {
            if (file.get(i).matches("^[a-zA-Z]+$")) {
                int numberOfNeighbors = Integer.parseInt(file.get(++i));
                for (int j = 1; j <= numberOfNeighbors; j++) {
                    String[] s = file.get(i + j).split(" ");
                    matrix[tmp][Integer.parseInt(s[0]) - 1] = Integer.parseInt(s[1]);
                }
                tmp++;
            }
        }
        return matrix;
    }

    private List<String> getDestinationCities(List<String> file, int numbersOfCities) {
        List<String> destinationCities = new ArrayList<>();


        return null;
    }


}

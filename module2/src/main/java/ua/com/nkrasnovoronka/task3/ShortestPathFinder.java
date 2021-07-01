package ua.com.nkrasnovoronka.task3;

import ua.com.nkrasnovoronka.task3.exception.PathFinderException;
import ua.com.nkrasnovoronka.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShortestPathFinder {

    private static final int MAX_PATH_PRICE = 200000;
    private static final int MAX_CITY_NUMBER = 10000;
    private static final int MAX_PATH_NUMBER = 100;

    private List<String> cities;
    private int[][] citiesPaths;
    private String[][] cheapestPaths;


    public void findCheapestPath(String inputFile, String outputFile) {
        parseData(inputFile);
        List<String> foundedPaths = findAllPaths().stream().map(Object::toString).collect(Collectors.toList());
        FileUtil.writeToFile(foundedPaths, outputFile);
    }

    private List<Integer> findAllPaths() {
        List<Integer> resultShortestPaths = new ArrayList<>();
        for (String[] path : cheapestPaths) {
            resultShortestPaths.add(dijkstraShortestPath(cities.indexOf(path[0]), cities.indexOf(path[1])));
        }
        return resultShortestPaths;
    }

    private int dijkstraShortestPath(int start, int end) {
        boolean[] visited = new boolean[cities.size()];
        int[] distances = new int[cities.size()];
        int priceLimit = MAX_PATH_PRICE;
        Arrays.fill(distances, priceLimit);
        distances[start] = 0;

        for (int i = 0; i < cities.size(); i++) {
            int temp = -1;
            int tempDistance = priceLimit;
            for (int j = 0; j < cities.size(); j++) {
                if (visited[i] || tempDistance < distances[i]) {
                    continue;
                }
                temp = i;
                tempDistance = distances[i];
            }
            if (temp == -1) {
                break;
            }
            for (int k = 0; k < cities.size(); k++) {
                int weight = citiesPaths[temp][k];
                if (citiesPaths[temp][k] != 0 && distances[temp] + weight < distances[k]) {
                    distances[k] = distances[temp] + weight;
                }
            }
            visited[temp] = true;
        }

        return distances[end];
    }

    private void parseData(String input){
        File inputFile = new File(input);
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            int citiesNumber = Integer.parseInt(br.readLine());
            if (citiesNumber > MAX_CITY_NUMBER) {
                throw new PathFinderException("Cities number cannot be more than 10000");
            }

            createCitiesList(br, citiesNumber);

            int numberOfPathsToFind = Integer.parseInt(br.readLine());

            if (numberOfPathsToFind > MAX_PATH_NUMBER) {
                throw new PathFinderException("To many paths set to be found");
            }

            getCityNameToFind(br, numberOfPathsToFind);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createCitiesList(BufferedReader br, int citiesNumber) throws IOException {
        cities = new ArrayList<>();
        citiesPaths = new int[citiesNumber][citiesNumber];
        for (int i = 0; i < citiesNumber; i++) {
            cities.add(br.readLine());
            int neighborNumber = Integer.parseInt(br.readLine());
            for (int j = 0; j < neighborNumber; j++) {
                String[] neighbor = br.readLine().split(" ");
                int neighborIndex = Integer.parseInt(neighbor[0]);
                int pathCost = Integer.parseInt(neighbor[1]);

                if (neighborIndex < 1 || pathCost < 0) {
                    throw new PathFinderException("Invalid neighbor or path cost value");
                }

                citiesPaths[i][neighborIndex - 1] = pathCost;
            }
        }
    }

    private void getCityNameToFind(BufferedReader br, int numberOfPathsToFind) throws IOException {
        cheapestPaths = new String[numberOfPathsToFind][2];
        for (int i = 0; i < numberOfPathsToFind; i++) {
            String[] citiesInPath = br.readLine().split(" ");
            cheapestPaths[i][0] = citiesInPath[0];
            cheapestPaths[i][1] = citiesInPath[1];
        }
    }
}

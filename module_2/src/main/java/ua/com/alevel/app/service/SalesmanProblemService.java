package ua.com.alevel.app.service;

import ua.com.alevel.app.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SalesmanProblemService {

    public void findCheapest(String inFile, String outFile) {

        File inputFile = new File(inFile);
        List<String> cities = new ArrayList<>();
        String[][] allPaths = new String[0][];
        int[][] paths = new int[0][];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int numCity = Integer.parseInt(reader.readLine());

            if (numCity > 10000) {
                throw new IllegalArgumentException("Number of cities must be lower or equals to 10000");
            }

            paths = new int[numCity][numCity];
            for (int i = 0; i < numCity; i++) {
                cities.add(reader.readLine());
                int p = Integer.parseInt(reader.readLine());
                for (int j = 0; j < p; j++) {
                    String[] neighbors = reader.readLine().split(" ");
                    int nIndex = Integer.parseInt(neighbors[0]);
                    int cost = Integer.parseInt(neighbors[1]);

                    if (nIndex < 1) {
                        throw new IllegalArgumentException("Neighbour index must be 1 or greater");
                    }
                    if (cost < 1) {
                        throw new IllegalArgumentException("Path cost can not be lower then 1");
                    }
                    paths[i][nIndex - 1] = cost;
                }
            }

            int numPath = Integer.parseInt(reader.readLine());

            if (numPath > 100) {
                throw new IllegalArgumentException("Number of paths can not be greater than 100");
            }

            allPaths = new String[numPath][2];
            for (int i = 0; i < numPath; i++) {
                String[] citiesToFind = reader.readLine().split(" ");
                allPaths[i][0] = citiesToFind[0];
                allPaths[i][1] = citiesToFind[1];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> cheapestPaths = new ArrayList<>();
        for (String[] path : allPaths) {
            boolean[] visited = new boolean[cities.size()];
            int[] length = new int[cities.size()];
            Arrays.fill(length, 20000);
            length[cities.indexOf(path[0])] = 0;

            for (int i = 0; i < cities.size(); i++) {
                int tmp = -1;
                int tmpLength = 20000;
                for (int j = 0; j < cities.size(); j++) {
                    if (visited[i] || tmpLength < length[i]) {
                        continue;
                    }
                    tmp = i;
                    tmpLength = length[i];
                }
                if (tmp == -1) {
                    break;
                }
                for (int j = 0; j < cities.size(); j++) {
                    int weight = paths[tmp][j];
                    if (paths[tmp][j] != 0 && length[tmp] + weight < length[j]) {
                        length[j] = length[tmp] + weight;
                    }
                }
                visited[tmp] = true;
            }
            cheapestPaths.add(length[cities.indexOf(path[1])]);
        }

        List<String> foundedPaths = cheapestPaths
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        FileUtils.writeFile(foundedPaths, outFile);
    }
}

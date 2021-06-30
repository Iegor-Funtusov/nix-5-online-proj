package Task3;

import java.util.List;
import java.util.Map;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Solution {

    public static int getSolution(List<City> cities, int start, int end){
        int[][] adjacencyMatrix = buildAdjacencyMatrix(cities);
        int []shortestPaths = dijkstraAlgorithm(adjacencyMatrix, start);
        int resultPath = shortestPaths[end];
        if(resultPath > 200_000){
            throw new RuntimeException("Paths between cities does not exist");
        }
        return resultPath;
    }

    //Построение матрицы смежности графа. Значение в ячейке это стоимость пути. Если прямого пути нет, то MAX_VALUE инта
    private static int[][] buildAdjacencyMatrix(List<City> cities){
        int quantity = cities.size();
        int[][] adjacencyMatrix = new int[quantity][quantity];
        Map<Integer, Integer> neighbourValue;

        for(int i = 0; i < cities.size(); i++){
            neighbourValue = cities.get(i).getNeighbourValueMap();
            for(int j = 0; j < cities.size(); j++){
                int cityIndex = j + 1;
                Integer value = neighbourValue.get(cityIndex);
                if(value != null){
                    adjacencyMatrix[i][j] = value;
                } else {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        return adjacencyMatrix;
    }


    //Алгоритм Дейкстры для поиска массива минимальных путей из заданной вершины
    private static int[] dijkstraAlgorithm(int[][] adjacencyMatrix, int start) {
        int INF = Integer.MAX_VALUE / 2;    // "Бесконечность"
        int vNum = adjacencyMatrix.length;  // количество вершин
        boolean[] used = new boolean [vNum]; // массив пометок
        int[] dist = new int [vNum]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

        fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
        dist[start] = 0; // для начальной вершины положим 0

        for (;;) {
            int v = -1;
            for (int nv = 0; nv < vNum; nv++) // перебираем вершины
                if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                    v = nv;
                if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее
            for (int nv = 0; nv < vNum; nv++)
                if (!used[nv] && adjacencyMatrix[v][nv] < INF) // для всех непомеченных смежных
                    dist[nv] = min(dist[nv], dist[v] + adjacencyMatrix[v][nv]); // улучшаем оценку расстояния
        }

        return dist;
    }

}
package task3;
public class ShortestPath {
        int cities;
        int [][] graph;
        String[] citiesNames;

    public int getCityIndex(String s){
        for(int i = 0; i < citiesNames.length; i++) {
            if (citiesNames[i].equals(s)) {
                return i;
            }
        }
        throw new IllegalArgumentException("City doesn`t exist");
    }

    public void setCities(int cities) {
        if(cities <= 10000 && cities > 0){
            this.cities = cities;
        }
        else {
            throw new IllegalArgumentException("Cities` count should be less than 10000");
        }
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    public void setCitiesNames(String[] citiesnames) {
        this.citiesNames = citiesnames;
    }

    int minDistance(int[] path_array, boolean[] sptSet)   {
            int min = Integer.MAX_VALUE,
                minV = -1;
            for (int v = 0; v < cities; v++)
                if (!sptSet[v] && path_array[v] <= min) {
                    min = path_array[v];
                    minV = v;
                }
            return minV;
        }

        public int dijkstra(int src_node, int end_node)  {
            int[] paths = new int[cities];
            boolean[] visited = new boolean[cities];
            for (int i = 0; i < cities; i++) {
                paths[i] = Integer.MAX_VALUE;
                visited[i] = false;
            }
            paths[src_node] = 0;
            for (int count = 0; count < cities - 1; count++) {
                int u = minDistance(paths, visited);
                visited[u] = true;
                for (int v = 0; v < cities; v++)
                    if (!visited[v] && graph[u][v] != 0
                            && paths[u] != Integer.MAX_VALUE
                            && paths[u] + graph[u][v] < paths[v])
                        paths[v] = paths[u] + graph[u][v];
            }
            return paths[end_node];
        }
    }

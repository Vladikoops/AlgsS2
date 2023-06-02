package by.bsu.algorithms.lab2.greed;

import by.bsu.algorithms.lab2.greed.GreedyAlgorithm;
import by.bsu.algorithms.lab2.greed.InitialRouteType;
import by.bsu.algorithms.lab2.greed.LocalSearch;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        var graph = generateRandomGraphMatrix(10000, 1, 100);
        Arrays.stream(graph)
                .forEach(row -> System.out.println(Arrays.toString(row)));
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(graph);
        int[] greedyResult = greedyAlgorithm.buildRoute();
        System.out.println("greedy result " + Arrays.toString(greedyResult));
        System.out.println("greedyLength " + calculateRouteLength(greedyResult, graph));
        LocalSearch search = new LocalSearch(graph);
        search.doAlgorithm(InitialRouteType.RANDOM);
        System.out.println();
        search.doAlgorithm(InitialRouteType.GREEDY);
    }

    public static int[][] generateRandomGraphMatrix(int size, int minEdgeLength, int maxEdgeLength) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i < j) {
                    matrix[i][j] = matrix[j][i] = random.nextInt(minEdgeLength, maxEdgeLength);
                }
            }
        }
        return matrix;
    }

    public static int calculateRouteLength(int[] route, int[][] graph) {
        int routeLength = 0;
        int endIndex = route.length - 1;
        for (int i = 0; i < endIndex; i++) {
            routeLength += graph[route[i]][route[i + 1]];
        }
        routeLength += graph[route[0]][route[endIndex]];
        return routeLength;
    }
}

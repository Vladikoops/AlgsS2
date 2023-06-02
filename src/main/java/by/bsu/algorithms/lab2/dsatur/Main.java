package by.bsu.algorithms.lab2.dsatur;

import by.bsu.algorithms.lab2.dsatur.Gis;
import by.bsu.algorithms.lab2.dsatur.entity.Graph;


public class Main {
    public static void main(String[] args) {
//        boolean[][] adjacentVertices = new boolean[][] {
//                {false, true, false, false, false, true, true}, // a
//                {true, false, true, false, false, false, true}, // b
//                {false, true, false, true, false, false, true}, // c
//                {false, false, true, false, true, false, true}, // d
//                {false, false, false, true, false, true, true}, // e
//                {true, false, false, false, true, false, true}, // f
//                {true, true, true, true, true, true, false} // g
//        };

        boolean[][] adjacentVertices = new boolean[][] {
                {false, true, false, false, true}, // 1
                {true, false, true, false, true}, // 2
                {false, true, false, true, true}, // 3
                {false, false, true, false, true}, // 4
                {true, true, true, true, false}, // 5
        };

        if (isValidMatrix(adjacentVertices)) {
            Graph graph = new Graph(adjacentVertices);
            System.out.println(graph);
            System.out.println();

            int answer = Gis.colorGraph(graph);
            System.out.println();
            System.out.println(graph);
            System.out.println("Used colors: " + answer);
        }
    }

    public static boolean isValidMatrix(boolean[][] adjacentVertices) {
        int n = adjacentVertices.length;
        for (int i = 0; i < n; i++) {
            if (adjacentVertices[i] == null || adjacentVertices[i].length != n) {
                return false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && adjacentVertices[i][j]) {
                    return false;
                }
                if (adjacentVertices[i][j] != adjacentVertices[j][i]) {
                    return false;
                }
                if (i < j) {
                    break;
                }
            }
        }
        return true;
    }
}

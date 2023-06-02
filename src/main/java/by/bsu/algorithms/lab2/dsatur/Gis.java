package by.bsu.algorithms.lab2.dsatur;

import by.bsu.algorithms.lab2.dsatur.entity.Graph;
import by.bsu.algorithms.lab2.dsatur.entity.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Gis {
    private Gis() {
    }
    public static int colorGraph(Graph graph) {
        Set<Vertex> uncoloredVertices = new HashSet<>(graph.getVertices());
        int colorIndex = 1;
        while (!uncoloredVertices.isEmpty()) {
            Set<Vertex> availableVertices = new HashSet<>(uncoloredVertices);
            while (!availableVertices.isEmpty()) {
                Vertex vertexWithMinDegree = findVertexWithMinimalDegree(availableVertices);
                vertexWithMinDegree.color(colorIndex);
                removeVertexWithItsNeighbours(availableVertices, vertexWithMinDegree);
            }
            colorIndex++;
            uncoloredVertices = findUncloloredVertices(graph);
        }
        return graph.computeColorsAmount();
    }

    private static Vertex findVertexWithMinimalDegree(Set<Vertex> vertices) {
        int minDegree = Integer.MAX_VALUE;
        Vertex vertexWithMinDegree = null;
        for (Vertex vertex : vertices) {
            int degree = vertex.getVertexDegree();
            if (degree < minDegree) {
                minDegree = degree;
                vertexWithMinDegree = vertex;
            }
        }
        return vertexWithMinDegree;
    }

    private static void removeVertexWithItsNeighbours(Set<Vertex> availableVertices, Vertex vertexWithMinDegree) {
        var adjacentVertices = vertexWithMinDegree.getAdjacentVertices();
        availableVertices.remove(vertexWithMinDegree);
        adjacentVertices.forEach(availableVertices::remove);
    }

    private static Set<Vertex> findUncloloredVertices(Graph graph) {
        return graph.getVertices().stream()
                .filter(v -> !v.isColored())
                .collect(Collectors.toSet());
    }
}

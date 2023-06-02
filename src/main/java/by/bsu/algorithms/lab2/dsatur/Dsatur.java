package by.bsu.algorithms.lab2.dsatur;

import by.bsu.algorithms.lab2.dsatur.entity.Graph;
import by.bsu.algorithms.lab2.dsatur.entity.Vertex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dsatur {
    private Dsatur() {
    }

    public static int colorGraph(Graph graph) {
        Set<Vertex> uncoloredVertices = new HashSet<>(graph.getVertices());

        Vertex vertexWithMaxDegree = graph.findVertexWithHighestDegree();
        vertexWithMaxDegree.color(1);
        uncoloredVertices.remove(vertexWithMaxDegree);

        while (!uncoloredVertices.isEmpty()) {
            Vertex uncoloredVertexWithHighestDegreeOfSaturation = findVertexWithHighestDegreeOfSaturation(uncoloredVertices);
            colorVertex(uncoloredVertexWithHighestDegreeOfSaturation);
            uncoloredVertices.remove(uncoloredVertexWithHighestDegreeOfSaturation);
        }
        return graph.computeColorsAmount();
    }

    private static Vertex findVertexWithHighestDegreeOfSaturation(Set<Vertex> uncoloredVertices) {
        int maxDegreeOfSaturation = -1;
        Vertex vertexWithMaxDegreeOfSaturation = null;
        for (var vertex: uncoloredVertices) {
            int degreeOfSaturation = findDegreeOfSaturation(vertex);
            if (degreeOfSaturation > maxDegreeOfSaturation) {
                maxDegreeOfSaturation = degreeOfSaturation;
                vertexWithMaxDegreeOfSaturation = vertex;
            }
        }
        return vertexWithMaxDegreeOfSaturation;
    }

    public static int findDegreeOfSaturation(Vertex vertex) {
        Set<Vertex> adjacentVertices = vertex.getAdjacentVertices();
        return (int) adjacentVertices.stream()
                .filter(Vertex::isColored)
                .mapToInt(Vertex::getColor)
                .distinct()
                .count();
    }

    private static void colorVertex(Vertex vertex) {
        int minColor = findMinUnusedColor(vertex);
        vertex.color(minColor);
    }

    private static int findMinUnusedColor(Vertex vertex) {
        var coloredVertices = vertex.getAdjacentVertices()
                .stream()
                .filter(Vertex::isColored)
                .toList();
        int[] colors = getSortedUsedColors(coloredVertices);
        int i = 0;
        while (i < colors.length && colors[i] == i + 1) {
            i++;
        }
        return i + 1;
    }

    private static int[] getSortedUsedColors(List<Vertex> coloredVertices) {
        int[] colors = coloredVertices.stream()
                .mapToInt(Vertex::getColor)
                .toArray();
        Arrays.sort(colors);
        return colors;
    }
}

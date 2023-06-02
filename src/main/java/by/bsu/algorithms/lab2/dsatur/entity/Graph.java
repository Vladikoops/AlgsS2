package by.bsu.algorithms.lab2.dsatur.entity;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class Graph {
    private final List<Vertex> vertices;

    public Graph(@NonNull boolean[][] edgeMatrix) {
        vertices = new ArrayList<>(edgeMatrix.length);
        initVertices(edgeMatrix.length);
        addAdjacentVertices(edgeMatrix);
    }

    private void initVertices(int amount) {
        for (int i = 0; i < amount; i++) {
            vertices.add(new Vertex(Integer.toString(i)));
        }
    }

    private void addAdjacentVertices(boolean[][] edgeMatrix) {
        for (int i = 0; i < edgeMatrix.length; i++) {
            Vertex vertex = vertices.get(i);
            for (int j = 0; j < edgeMatrix.length; j++) {
                if (edgeMatrix[i][j]) {
                    vertex.addAdjacentVertex(vertices.get(j));
                }
            }
        }
    }

    public Optional<Vertex> findVertexByName(String name) {
        return vertices.stream()
                .dropWhile(v -> !v.getName().equals(name))
                .findFirst();
    }

    public Vertex findVertexWithHighestDegree() {
        int maxDegree = 0;
        Vertex vertexWithMaxDegree = null;
        for (var vertex: vertices) {
            int degree = vertex.getVertexDegree();
            if (degree > maxDegree) {
                maxDegree = degree;
                vertexWithMaxDegree = vertex;
            }
        }
        return vertexWithMaxDegree;
    }

    public int computeColorsAmount() {
        return vertices.stream()
                .filter(Vertex::isColored)
                .mapToInt(Vertex::getColor)
                .max()
                .orElse(0);
    }

    public List<Vertex> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        return vertices.equals(graph.vertices);
    }

    @Override
    public int hashCode() {
        return vertices != null ? vertices.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph{");
        StringJoiner joiner = new StringJoiner(",\n", "[", "]");
        vertices.forEach(v -> joiner.add(v.toString()));
        sb.append("vertices=").append(joiner);
        sb.append('}');
        return sb.toString();
    }
}

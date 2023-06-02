package by.bsu.algorithms.lab2.dsatur.entity;

import lombok.NonNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

public class Vertex {
    private final @NonNull String name;
    private final @NonNull Set<Vertex> adjacentVertices;
    private int color;
    private boolean isColored;

    public Vertex(String name) {
        this(name, new HashSet<>());
    }

    public Vertex(String name, Set<Vertex> adjacentVertices) {
        this.name = name;
        this.adjacentVertices = adjacentVertices;
    }

    public Optional<Vertex> findAdjacentVertex(String name) {
        return adjacentVertices.stream()
                .dropWhile(v -> !v.getName().equals(name))
                .findFirst();
    }

    public boolean addAdjacentVertex(Vertex vertex) {
        return adjacentVertices.add(vertex);
    }

    public Set<Vertex> getAdjacentVertices() {
        return adjacentVertices;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public boolean color(int color) {
        if (isColored) {
            return false;
        }
        this.color = color;
        isColored = true;
        return true;
    }

    public boolean isColored() {
        return isColored;
    }

    public int getVertexDegree() {
        return adjacentVertices.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (color != vertex.color) return false;
        if (!name.equals(vertex.name)) return false;
        for (var v1 : adjacentVertices) {
            boolean temp = false;
            for (var v2 : vertex.adjacentVertices) {
                if (v1.equalsByNameAndColor(v2)) {
                    temp = true;
                    break;
                }
            }
            if (!temp) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31;
    }

    public boolean equalsByNameAndColor(Vertex vertex) {
        return color == vertex.color && name.equals(vertex.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vertex{");
        sb.append("name='").append(name).append('\'');
        sb.append(", color=").append(color);
        sb.append(", adjacentVertices=");
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        adjacentVertices.forEach(v -> joiner.add(v.name));
        sb.append(joiner);
        sb.append('}');
        return sb.toString();
    }
}

package by.bsu.algorithms.lab3.entity;

import java.util.ArrayList;
import java.util.List;

public class Pack {
    public static final Double MAX_LOAD = 1.0;
    private final List<Double> load;

    public Pack() {
        load = new ArrayList<>();
    }

    public Pack(Double cargo) {
        this();
        load.add(cargo);
    }

    public boolean addCargoIfPossible(Double cargo) {
        if (isPossibleToAdd(cargo)) {
            return load.add(cargo);
        }
        return false;
    }

    public boolean isPossibleToAdd(Double cargo) {
        return MAX_LOAD - (getLoad() + cargo) >= 0;
    }

    public Double remainLoad() {
        return MAX_LOAD - getLoad();
    }

    public Double getLoad() {
        return load.stream()
                .reduce(Double::sum)
                .orElse(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pack pack = (Pack) o;

        return load != null ? load.equals(pack.load) : pack.load == null;
    }

    @Override
    public int hashCode() {
        return load != null ? load.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pack{");
        sb.append("load=").append(load);
        sb.append('}');
        return sb.toString();
    }
}

package by.bsu.algorithms.lab3.algorithm;

import by.bsu.algorithms.lab3.entity.Pack;

import java.util.ArrayList;
import java.util.List;

public class BestFitDecreasing {
    private BestFitDecreasing() {
    }

    public static List<Pack> wrapUp(List<Double> cargo) {
        List<Double> copy = new ArrayList<>(cargo);
        copy.sort((d1, d2) -> -Double.compare(d1, d2));
        return BestFit.wrapUp(copy);
    }
}

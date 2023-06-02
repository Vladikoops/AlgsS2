package by.bsu.algorithms.lab3.algorithm;

import by.bsu.algorithms.lab3.entity.Pack;

import java.util.ArrayList;
import java.util.List;

public class BestFit {
    private BestFit() {
    }

    public static List<Pack> wrapUp(List<Double> cargo) {
        List<Pack> boxes = new ArrayList<>();
        cargo.forEach(item -> {
            Pack bestFit = findMostSuitablePack(boxes, item);
            if (boxes.contains(bestFit)) {
                bestFit.addCargoIfPossible(item);
            }
            else {
                boxes.add(bestFit);
            }
        });
        return boxes;
    }

    private static Pack findMostSuitablePack(List<Pack> boxes, double item) {
        Pack bestFit = new Pack(item);
        for (Pack box : boxes) {
            if (box.isPossibleToAdd(item)) {
                if (box.getLoad() + item > bestFit.getLoad()) {
                    bestFit = box;
                }
            }
        }
        return bestFit;
    }
}

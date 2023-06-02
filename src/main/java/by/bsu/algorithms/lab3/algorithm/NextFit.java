package by.bsu.algorithms.lab3.algorithm;

import by.bsu.algorithms.lab3.entity.Pack;

import java.util.ArrayList;
import java.util.List;

public class NextFit {
    private NextFit() {
    }

    public static List<Pack> wrapUp(List<Double> cargo) {
        List<Pack> boxes = new ArrayList<>();
        boxes.add(new Pack(cargo.get(0)));
        for (int i = 1; i < cargo.size(); i++) {
            Pack lastBox = getLast(boxes);
            Double item = cargo.get(i);
            if (!lastBox.addCargoIfPossible(item)) {
                boxes.add(new Pack(item));
            }
        }
        return boxes;
    }

    private static Pack getLast(List<Pack> boxes) {
        return boxes.get(boxes.size() - 1);
    }
}

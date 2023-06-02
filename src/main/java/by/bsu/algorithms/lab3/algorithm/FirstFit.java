package by.bsu.algorithms.lab3.algorithm;

import by.bsu.algorithms.lab3.entity.Pack;

import java.util.ArrayList;
import java.util.List;

public class FirstFit {
    private FirstFit() {
    }

    public static List<Pack> wrapUp(List<Double> cargo) {
        List<Pack> boxes = new ArrayList<>();
        cargo.forEach(item -> {
           for (Pack box : boxes) {
               if (box.addCargoIfPossible(item)) {
                   return;
               }
           }
           boxes.add(new Pack(item));
        });
        return boxes;
    }
}

package by.bsu.algorithms.lab3;

import by.bsu.algorithms.lab3.algorithm.BestFit;
import by.bsu.algorithms.lab3.algorithm.BestFitDecreasing;
import by.bsu.algorithms.lab3.algorithm.FirstFit;
import by.bsu.algorithms.lab3.algorithm.FirstFitDecreasing;
import by.bsu.algorithms.lab3.algorithm.NextFit;
import by.bsu.algorithms.lab3.entity.Pack;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> cargo = List.of(0.2, 0.3, 0.7, 0.8, 0.6, 0.2, 0.2);
//        List<Double> cargo = List.of(0.7, 0.4, 0.3, 0.6, 0.5, 0.6, 0.5, 0.4);
        printResult(FirstFit.wrapUp(cargo));
        System.out.println();

        printResult(NextFit.wrapUp(cargo));
        System.out.println();

        printResult(BestFit.wrapUp(cargo));
        System.out.println();

        printResult(BestFitDecreasing.wrapUp(cargo));
        System.out.println();

        printResult(FirstFitDecreasing.wrapUp(cargo));
    }

    public static void printResult(List<Pack> boxes) {
        boxes.forEach(System.out::println);
        System.out.println("Количество потребовавшихся упаковак: " + boxes.size());

    }
}

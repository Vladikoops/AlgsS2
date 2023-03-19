package by.bsu.algorithms.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 2.3. Г={ | }. Считая слово P записью числа в единичной системе счисления,
 * увеличить это число на 2.
 */
public class Part2 {
    private static final State<Character> INITIAL_STATE = symbol -> switch (symbol) {
        case '|' -> new StateActionResult<>('|', 1, Part2.INITIAL_STATE);
        case '#' -> new StateActionResult<>('#', 0, Part2.ADD_FIRST);
        case default -> throw new RuntimeException("Illegal state");
    };

    private static final State<Character> ADD_FIRST = symbol -> switch (symbol) {
        case '#' -> new StateActionResult<>('|', 1, Part2.ADD_SECOND);
        case default -> throw new RuntimeException("Illegal symbol");
    };

    private static final State<Character> ADD_SECOND = symbol -> switch (symbol) {
        case '#' -> new StateActionResult<>('|', 0, Part2.END);
        case default -> throw new RuntimeException("Illegal symbol");
    };

    private static final State<Character> END = symbol -> new StateActionResult<>(symbol, 0, null);

    private static String convertNumber(int number) {
        return "|".repeat(Math.max(0, number));
    }

    public static void main(String[] args) {
        System.out.println("Введите целое число");
        Scanner scanner = new Scanner(System.in);
        int number = Math.abs(scanner.nextInt());
        String input = convertNumber(number); // переводит число в единичную CC

        input = '#' + input + "#".repeat(2); // добавление пустых символов
        List<Character> word = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            word.add(input.charAt(i));
        }
        int index = 1;
        State<Character> currentState = INITIAL_STATE;

        while (true) { // работа машины Тьюринга
            StateActionResult<Character> actionResult = currentState.doAction(word.get(index));
            word.set(index, actionResult.getResult());
            index += actionResult.getIndexChange();
            currentState = actionResult.getNewState();
            if (currentState == null) {
                break;
            }
        }
        word = word.stream()
                .filter(s -> s != '#')
                .toList();
        System.out.println("Answer:" + word);
        System.out.println(word.size());
    }
}

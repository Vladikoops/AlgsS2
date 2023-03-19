package by.bsu.algorithms.lab1;

import java.util.Scanner;

/**
 * 1.3. Г={0,1}. Для непустого слова P определить, является ли оно записью
 * степени двойки (1, 2, 4, 8, …) в двоичной системе счисления. Ответ: слово 1
 * (является) или слово 0.
 */
public class Part1 {
    private static final State<Character> INITIAL_STATE = symbol -> switch (symbol) {
        case '0' -> new StateActionResult<>('0', 1, Part1.NEGATIVE_ANSWER);
        case '1' -> new StateActionResult<>('1', 1, Part1.PASS_ZEROES_STATE);
        case default -> throw new RuntimeException("Illegal state");
    };

    private static final State<Character> PASS_ZEROES_STATE = symbol -> switch (symbol) {
        case '0' -> new StateActionResult<>('0', 1, Part1.PASS_ZEROES_STATE);
        case '1' -> new StateActionResult<>('1', 0, Part1.GO_TO_END_WITH_NEGATIVE_ANSWER);
        case '#' -> new StateActionResult<>('#', -1, Part1.POSITIVE_ANSWER);
        case default -> throw new RuntimeException("Illegal symbol");
    };

    private static final State<Character> POSITIVE_ANSWER = symbol -> switch (symbol) {
        case '0', '1' -> new StateActionResult<>('#', -1, Part1.POSITIVE_ANSWER);
        case '#' -> new StateActionResult<>('1', 0, Part1.END);
        case default -> throw new RuntimeException("Illegal symbol");
    };

    private static final State<Character> GO_TO_END_WITH_NEGATIVE_ANSWER = symbol -> switch (symbol) {
        case '0' -> new StateActionResult<>('0', 1, Part1.GO_TO_END_WITH_NEGATIVE_ANSWER);
        case '1' -> new StateActionResult<>('1', 1, Part1.GO_TO_END_WITH_NEGATIVE_ANSWER);
        case '#' -> new StateActionResult<>('#', -1, Part1.NEGATIVE_ANSWER);
        case default -> throw new RuntimeException("Illegal symbol");
    };

    private static final State<Character> NEGATIVE_ANSWER = symbol -> switch (symbol) {
        case '0', '1' -> new StateActionResult<>('#', -1, Part1.NEGATIVE_ANSWER);
        case '#' -> new StateActionResult<>('0', 0, Part1.END);
        case default -> throw new RuntimeException("Illegal symbol");
    };

    private static final State<Character> END = symbol -> new StateActionResult<>(symbol, 0, null);

    public static void main(String[] args) {
        System.out.println("Введите слово состоящее из 0 и 1");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        input = '#' + input + '#'; // добавление пустых символов
        char[] word = input.toCharArray();
        int index = 1;
        State<Character> currentState = INITIAL_STATE;

        while (true) { // работа машины Тьюринга
            StateActionResult<Character> actionResult = currentState.doAction(word[index]);
            word[index] = actionResult.getResult();
            index += actionResult.getIndexChange();
            currentState = actionResult.getNewState();
            if (currentState == null) {
                break;
            }
        }
        // удаление всех пустых символов
        String answer = new String(word, 0, word.length).replaceAll("#", "");
        System.out.println("Answer:" + answer);
    }


}

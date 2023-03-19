package by.bsu.algorithms.lab1;

@FunctionalInterface
public interface State<T> {
    StateActionResult<T> doAction(T input);
}

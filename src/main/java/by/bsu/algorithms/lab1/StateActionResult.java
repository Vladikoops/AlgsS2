package by.bsu.algorithms.lab1;

public class StateActionResult<T> {
    private T result;
    private int indexChange;
    private State<T> newState;

    public StateActionResult(T result, int indexChange, State<T> newState) {
        this.newState = newState;
        this.indexChange = indexChange;
        this.result = result;
    }

    public StateActionResult() {
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getIndexChange() {
        return indexChange;
    }

    public void setIndexChange(int indexChange) {
        this.indexChange = indexChange;
    }

    public State<T> getNewState() {
        return newState;
    }

    public void setNewState(State<T> newState) {
        this.newState = newState;
    }
}

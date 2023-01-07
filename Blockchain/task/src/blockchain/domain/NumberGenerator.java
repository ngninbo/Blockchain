package blockchain.domain;

public class NumberGenerator {

    private static final NumberGenerator instance = new NumberGenerator();
    private volatile int value = 0;

    public static NumberGenerator getInstance() {
        return instance != null ? instance : new NumberGenerator();
    }

    public synchronized int next() {
        return ++value;
    }
}

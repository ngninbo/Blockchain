package blockchain.domain;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BlockchainCollector<T> {

    private Set<T> messages = new CopyOnWriteArraySet<>();

    public void push(T t) {
        messages.add(t);
    }

    public Set<T> get() {
        return messages;
    }

    public void reset() {
        messages = new CopyOnWriteArraySet<>();
    }
}

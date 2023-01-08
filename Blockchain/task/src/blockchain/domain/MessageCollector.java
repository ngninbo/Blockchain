package blockchain.domain;

import blockchain.model.Message;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class MessageCollector {

    private Set<Message> messages = new CopyOnWriteArraySet<>();

    public void push(Message message) {
        messages.add(message);
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void reset() {
        messages = new CopyOnWriteArraySet<>();
    }
}

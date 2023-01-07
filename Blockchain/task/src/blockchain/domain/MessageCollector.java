package blockchain.domain;

import java.util.ArrayList;
import java.util.List;

public class MessageCollector {

    private List<String> messages = new ArrayList<>();

    public void push(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void reset() {
        messages = new ArrayList<>();
    }
}

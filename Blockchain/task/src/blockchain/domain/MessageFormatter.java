package blockchain.domain;

import blockchain.model.Message;

import java.util.Set;

public class MessageFormatter {

    public String format(Set<Message> messages) {

        return messages.stream()
                .map(message -> String.format("\n%s: %s", message.getSender().getName(), message.getContent()))
                .reduce((acc, data) -> acc + data)
                .orElse("no messages");
    }
}

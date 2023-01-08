package blockchain.formatter;

import blockchain.model.Message;

import java.util.Set;

public class MessageFormatter extends BlockchainFormatter<Message> {

    public String format(Set<Message> messages) {

        return messages.stream()
                .map(Message::description)
                .reduce((acc, data) -> acc + data)
                .orElse("no messages");
    }
}

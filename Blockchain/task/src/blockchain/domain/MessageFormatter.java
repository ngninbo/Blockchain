package blockchain.domain;

import java.util.List;

public class MessageFormatter {

    public String format(List<String> messages) {

        return messages.stream()
                .map(data -> "\n" + data)
                .reduce((acc, data) -> acc + data)
                .orElse("no messages");
    }
}

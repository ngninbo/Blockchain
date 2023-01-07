package blockchain.domain;

import java.util.List;

public class MessageFormatter {

    public String format(List<String> messages) {

        StringBuilder builder = new StringBuilder("\n");

        for (int i = 0; i < messages.size(); i++) {
            builder.append(messages.get(i));
            if (i < messages.size() - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}

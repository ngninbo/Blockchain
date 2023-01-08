package blockchain.domain;

import blockchain.model.Message;
import blockchain.model.User;

public class MessageBuilder {
    private int id;
    private String content;
    private User sender;

    private MessageBuilder() {
    }

    public static MessageBuilder init() {
        return new MessageBuilder();
    }

    public MessageBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public MessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder withSender(User sender) {
        this.sender = sender;
        return this;
    }

    public Message build() {
        return new Message(id, content, sender);
    }
}
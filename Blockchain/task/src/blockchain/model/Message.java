package blockchain.model;

import java.util.Base64;

public class Message {

    private final int id;
    private final String content;
    private String signature;
    private final User sender;

    public Message(int id, String content, User sender) {
        this.id = id;
        this.content = content;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public byte[] getSignature() {
        return Base64.getDecoder().decode(signature);
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String description() {
        return String.format("\n%s: %s", sender.getName(), content);
    }

    public User getSender() {
        return sender;
    }

    public byte[] getBytes() {
        return sender.getName().concat(content).concat(String.valueOf(id)).getBytes();
    }
}

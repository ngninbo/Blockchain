package blockchain.model;

import blockchain.Blockchain;

public class User {

    private final String name;
    private final Blockchain blockchain = Blockchain.getInstance();

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message){
        blockchain.collect(String.format("%s: %s", name, message));
    }
}

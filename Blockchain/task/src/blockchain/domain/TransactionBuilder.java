package blockchain.domain;

import blockchain.model.Transaction;
import blockchain.model.User;

public class TransactionBuilder {
    private int id;
    private User sender;
    private User receiver;
    private long amount;

    private TransactionBuilder() {
    }

    public static TransactionBuilder init() {
        return new TransactionBuilder();
    }

    public TransactionBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder withSender(User sender) {
        this.sender = sender;
        return this;
    }

    public TransactionBuilder withReceiver(User receiver) {
        this.receiver = receiver;
        return this;
    }

    public TransactionBuilder withAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public Transaction build() {
        return new Transaction(id, sender, receiver, amount);
    }
}
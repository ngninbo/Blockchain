package blockchain.model;

import java.util.Base64;
import java.util.Objects;

public class Transaction {

    private final int id;
    private final User sender;
    private final User receiver;
    private final long amount;
    private String signature;

    public Transaction(int id, User sender, User receiver, long amount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public long getAmount() {
        return amount;
    }

    public byte[] getSignature() {
        return Base64.getDecoder().decode(signature);
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String description() {
        return String.format("%s sent %s VC to %s", sender.getName(), amount, receiver.getName());
    }

    public byte[] getBytes() {
        return sender.getName()
                .concat(receiver.getName())
                .concat(String.valueOf(amount))
                .concat(String.valueOf(id))
                .getBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getId() == that.getId() && getAmount() == that.getAmount() && Objects.equals(getSender(), that.getSender()) && Objects.equals(getReceiver(), that.getReceiver());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSender(), getReceiver(), getAmount());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                '}';
    }
}

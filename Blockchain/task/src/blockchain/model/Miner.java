package blockchain.model;

import java.util.List;

public abstract class Miner {

    protected abstract Block mine();
    protected abstract void sendTransaction();

    public abstract void setTransaction(List<Transaction> transactions);
}

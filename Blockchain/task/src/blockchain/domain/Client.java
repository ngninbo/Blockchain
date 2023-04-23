package blockchain.domain;

import java.util.concurrent.ExecutionException;

public abstract class Client {

    protected abstract void initSender();
    protected abstract void initMiners();

    protected abstract void submit() throws ExecutionException, InterruptedException;

    public void mine() throws ExecutionException, InterruptedException {
        initMiners();
        initSender();
        submit();
    }
}

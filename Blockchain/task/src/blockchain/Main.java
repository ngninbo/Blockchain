package blockchain;

import blockchain.domain.BlockchainClient;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new BlockchainClient().mine();
    }
}

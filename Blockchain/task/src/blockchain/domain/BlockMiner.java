package blockchain.domain;

import blockchain.Blockchain;

import java.util.Date;
import java.util.concurrent.Callable;

public class BlockMiner implements Callable<Block> {

    private final Blockchain blockchain = Blockchain.getInstance();
    private final int minerId;

    public BlockMiner(int minerId) {
        this.minerId = minerId;
    }

    public Block mine() {

        long startTime = System.currentTimeMillis();

        Block block = BlockBuilder.init()
                .withMiner("miner # " + minerId)
                .withId(blockchain.size() + 1)
                .withTimeStamp(new Date().getTime())
                .withHashPreviousBlock(blockchain.size() == 0 ? "0" : blockchain.getLatestHash())
                .generateHash(blockchain.getComplexity())
                .createBlock();

        long duration = System.currentTimeMillis() - startTime;
        block.setCreationDuration(duration);

        return block;
    }

    @Override
    public Block call() {
        return mine();
    }
}

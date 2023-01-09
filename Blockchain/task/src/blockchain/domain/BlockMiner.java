package blockchain.domain;

import blockchain.Blockchain;
import blockchain.model.Block;

import java.util.Date;
import java.util.concurrent.Callable;

public class BlockMiner implements Callable<Block> {

    private final Blockchain blockchain = Blockchain.getInstance();
    private final String name;
    private int reward;

    public BlockMiner(int minerId) {
        this.name = "miner" + minerId;
    }

    public String getName() {
        return name;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Block mine() {

        long startTime = System.currentTimeMillis();

        Block block = BlockBuilder.init()
                .withMiner(this)
                .withId(blockchain.size() + 1)
                .withTimeStamp(new Date().getTime())
                .withHashPreviousBlock(blockchain.getLatestHash())
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

package blockchain.domain;

import blockchain.model.Block;
import blockchain.utils.BlockchainUtils;

import static blockchain.utils.BlockchainUtils.BLOCK_INIT_REWARD;

public class BlockBuilder {

    private BlockMiner miner;
    private long id;
    private long magicNumber;
    private long timeStamp;
    private String hashPreviousBlock;
    private String hash;

    private BlockBuilder() {
    }

    public static BlockBuilder init() {
        return new BlockBuilder();
    }

    public BlockBuilder withMiner(BlockMiner miner) {
        this.miner = miner;
        return this;
    }

    public BlockBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public BlockBuilder withTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public BlockBuilder withHashPreviousBlock(String hashPreviousBlock) {
        this.hashPreviousBlock = hashPreviousBlock;
        return this;
    }

    public BlockBuilder generateHash(int numberOfZeros) {
        magicNumber = BlockchainUtils.generateMagicNumber();
        String input = id + timeStamp + hashPreviousBlock;
        hash = BlockchainUtils.applySha256(input + magicNumber);

        String startingZeros = "0".repeat(numberOfZeros);

        while (!hash.startsWith(startingZeros)) {
            magicNumber = BlockchainUtils.generateMagicNumber();
            hash = BlockchainUtils.applySha256(input + magicNumber);
        }

        this.miner.setReward(BLOCK_INIT_REWARD);

        return this;
    }

    public Block createBlock() {
        return new Block(miner, id, timeStamp, magicNumber, hashPreviousBlock, hash);
    }
}

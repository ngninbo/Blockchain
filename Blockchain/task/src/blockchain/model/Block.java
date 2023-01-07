package blockchain.model;

import java.util.Objects;

public class Block {

    private final long id;
    private final String miner;
    private final long timeStamp;
    private final long magicNumber;
    private final String hashPreviousBlock;
    private final String hash;
    private long creationDuration;
    private String outcome;

    public Block(String miner, long id, long timeStamp, long magicNumber, String hashPreviousBlock, String hash) {
        super();
        this.miner = miner;
        this.id = id;
        this.timeStamp = timeStamp;
        this.magicNumber = magicNumber;
        this.hashPreviousBlock = hashPreviousBlock;
        this.hash = hash;
    }

    public long getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHashPreviousBlock() {
        return hashPreviousBlock;
    }

    public String getHash() {
        return hash;
    }

    public void setCreationDuration(long duration) {
        this.creationDuration = duration;
    }

    public long getCreationDuration() {
        return creationDuration;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public boolean isChainedWith(Block block) {
        return hashPreviousBlock.equals(block.getHash());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return getId() == block.getId() && getTimeStamp() == block.getTimeStamp() && magicNumber == block.magicNumber && getCreationDuration() == block.getCreationDuration() && Objects.equals(miner, block.miner) && Objects.equals(getHashPreviousBlock(), block.getHashPreviousBlock()) && Objects.equals(getHash(), block.getHash()) && Objects.equals(outcome, block.outcome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), miner, getTimeStamp(), magicNumber, getHashPreviousBlock(), getHash(), getCreationDuration(), outcome);
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Created by " + miner +
                "\nId: " + id +
                "\nTimestamp: " + timeStamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + hashPreviousBlock +
                "\nHash of the block:\n" + hash +
                "\nBlock was generating for " + creationDuration + " milliseconds" +
                "\n" + outcome + "\n";
    }
}

package blockchain.model;

import blockchain.domain.BlockMiner;
import blockchain.formatter.TransactionFormatter;
import blockchain.utils.ResourceProperties;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Set;

public class Block {

    private final long id;
    private final BlockMiner miner;
    private final long timeStamp;
    private final long magicNumber;
    private final String hashPreviousBlock;
    private final String hash;
    private long creationDuration;
    private String outcome;
    private Set<Transaction> transactions;

    public Block(BlockMiner miner, long id, long timeStamp, long magicNumber, String hashPreviousBlock, String hash) {
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

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block block = (Block) o;
        return getId() == block.getId() && getTimeStamp() == block.getTimeStamp() && magicNumber == block.magicNumber
                && getCreationDuration() == block.getCreationDuration() && Objects.equals(miner, block.miner) &&
                Objects.equals(getHashPreviousBlock(), block.getHashPreviousBlock()) &&
                Objects.equals(getHash(), block.getHash()) && Objects.equals(outcome, block.outcome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), miner, getTimeStamp(), magicNumber, getHashPreviousBlock(), getHash(), getCreationDuration(), outcome);
    }

    @Override
    public String toString() {
        return MessageFormat.format(ResourceProperties.getInstance().get("blockchain_block_info"),
                miner.getName(), miner.getName(), miner.getReward(), id, String.valueOf(timeStamp), String.valueOf(magicNumber), hashPreviousBlock, hash,
                TransactionFormatter.format(transactions), creationDuration, outcome);
    }
}

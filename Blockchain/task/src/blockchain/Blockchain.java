package blockchain;

import blockchain.domain.MessageCollector;
import blockchain.domain.MessageFormatter;
import blockchain.model.Block;
import java.util.Deque;
import java.util.LinkedList;

public class Blockchain {

    private static final Blockchain INSTANCE = new Blockchain();

    private final Deque<Block> blockDeque = new LinkedList<>();

    private final MessageCollector collector = new MessageCollector();

    private final MessageFormatter formatter = new MessageFormatter();

    private int complexity = 0;

    private Blockchain() {
    }

    public static Blockchain getInstance() {
        return INSTANCE;
    }

    public int size() {
        return blockDeque.size();
    }

    public Block getTail() {
        return blockDeque.peekLast();
    }

    public int getComplexity() {
        return complexity;
    }

    public void push(Block block) {

        if (isValid(block)) {
            adjustComplexity(block);
            block.setMessage(blockDeque.isEmpty() ? "no messages\n" : formatter.format(collector.getMessages()));
            blockDeque.add(block);
            collector.reset();
        }
    }

    public synchronized void collect(String message) {
        collector.push(message);
    }

    public void adjustComplexity(Block b) {

        if (b.getCreationDuration() < 10 || blockDeque.isEmpty()) {
            complexity++;
            b.setOutcome("N was increased to " + complexity);
        } else if (b.getCreationDuration() > 60 && complexity > 0) {
            complexity--;
            b.setOutcome("N was decreased by 1");
        } else {
            b.setOutcome("N stays the same");
        }
    }

    public boolean isValid(Block block) {
        return blockDeque.isEmpty() || block.isChainedWith(getTail());
    }

    public String getLatestHash() {
        return blockDeque.isEmpty() ? "0" : getTail().getHash();
    }
}

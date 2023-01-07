package blockchain;

import blockchain.domain.Block;
import java.util.Deque;
import java.util.LinkedList;

public class Blockchain {

    private static final Blockchain INSTANCE = new Blockchain();

    private final Deque<Block> blockDeque = new LinkedList<>();

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
            blockDeque.add(block);
            System.out.println(block);
        }
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

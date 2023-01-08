package blockchain;

import blockchain.domain.BlockchainCollector;
import blockchain.domain.NumberGenerator;
import blockchain.model.Block;
import blockchain.model.Transaction;
import blockchain.model.User;

import java.security.Signature;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Blockchain {

    private static final Blockchain INSTANCE = new Blockchain();

    private final Deque<Block> blockDeque = new ConcurrentLinkedDeque<>();
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    private final BlockchainCollector<Transaction> collector = new BlockchainCollector<>();

    private int complexity = 0;

    private Blockchain() {
    }

    public static Blockchain getInstance() {
        return INSTANCE != null ? INSTANCE : new Blockchain();
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

    public void add(User user) {
        userMap.putIfAbsent(user.getName(), user);
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void push(Block block) {

        if (isValid(block)) {
            adjustComplexity(block);

            final Set<Transaction> transactions = collector.get();
            block.setTransactions(transactions);
            processTransaction(transactions);

            blockDeque.add(block);
            collector.reset();
        }
    }

    private void processTransaction(Set<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            User sender = userMap.get(transaction.getSender().getName());

            if (sender.getBalance() < transaction.getAmount()) {
                return;
            }

            long balance = sender.getBalance() - transaction.getAmount();

            if (balance > 0) {
                sender.setBalance(balance);
                userMap.replace(sender.getName(), sender);
            }

            User recipient = transaction.getReceiver();
            balance = recipient.getBalance() + transaction.getAmount();
            recipient.setBalance(balance);
            userMap.replace(recipient.getName(), recipient);
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

    public synchronized void receive(Transaction transaction) throws Exception {

        if (transaction.getId() < size()) {
            return;
        }

        if (verifySignature(transaction)) {
            collector.push(transaction);
        }
    }

    public synchronized int next() {
        return NumberGenerator.getInstance().next();
    }

    private boolean verifySignature(Transaction transaction) throws Exception {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(transaction.getSender().getPublicKey());
        sig.update(transaction.getBytes());

        return sig.verify(transaction.getSignature());
    }
}

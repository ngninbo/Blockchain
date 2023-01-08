package blockchain.domain;

import blockchain.Blockchain;
import blockchain.model.Transaction;
import blockchain.model.User;
import blockchain.utils.BlockchainUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MessageSender implements Runnable {

    private final Blockchain blockchain = Blockchain.getInstance();

    private final User user;

    public MessageSender(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            Transaction transaction = prepareTransaction();
            user.sign(transaction);
            blockchain.receive(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Transaction prepareTransaction() {
        List<User> users = blockchain.getUserMap()
                .values()
                .stream()
                .filter(user1 -> !user1.getName().equals(user.getName()))
                .collect(Collectors.toList());
        Collections.shuffle(users);
        return TransactionBuilder
                .init()
                .withId(blockchain.next())
                .withSender(user)
                .withAmount(BlockchainUtils.generateAmount())
                .withReceiver(users.get(0))
                .build();
    }
}

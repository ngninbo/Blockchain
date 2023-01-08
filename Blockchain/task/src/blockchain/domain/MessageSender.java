package blockchain.domain;

import blockchain.Blockchain;
import blockchain.model.Message;
import blockchain.model.User;
import blockchain.utils.BlockchainUtils;

public class MessageSender implements Runnable {

    private final User user;

    public MessageSender(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            Message message = MessageBuilder.init()
                    .withId(Blockchain.getInstance().next())
                    .withSender(user)
                    .withContent(BlockchainUtils.getMessage(user.getName()))
                    .withId(NumberGenerator.getInstance().next())
                    .build();
            user.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

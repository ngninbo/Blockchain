package blockchain.domain;

import blockchain.model.User;

import java.util.Date;
import java.util.Random;

public class MessageSender implements Runnable {

    private final User user;

    public MessageSender(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            user.sendMessage(generateRandomMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomMessage() {

        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer + " " + new Date().getTime();
    }
}

package blockchain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class BlockchainUtils {

    public static final int NUMBER_OF_BLOCKS = 5;

    public static final List<Map.Entry<String, String>> DUMMY_MESSAGES = List.of(
            Map.entry("Tom", "Hey, I'm first!"),
            Map.entry("Sarah", "It's not fair!"),
            Map.entry("Sarah", "You always will be first because it is your blockchain!"),
            Map.entry("Sarah", "Anyway, thank you for this amazing chat."),
            Map.entry("Tom", "You're welcome :)"),
            Map.entry("Nick", "Hey Tom, nice chat"),
            Map.entry("Nick", "How did you do that?"),
            Map.entry("Tom", "Well, I asked Vladimir"),
            Map.entry("Tom", "You know him, right?"),
            Map.entry("Sarah", "Blah blah blah"),
            Map.entry("Nick", "Really? Oh wow"),
            Map.entry("Tom", "Yeah and blah blah blah...")
    );

    public static long generateMagicNumber() {
        final long max = 99999999;
        final long min = 10000000;
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandomMessage() {

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

    public static String getMessage(String userName) {
        List<String> messages = DUMMY_MESSAGES.stream()
                .filter(entry -> userName.equals(entry.getKey()))
                .parallel()
                .map(Map.Entry::getValue).collect(Collectors.toList());

        Collections.shuffle(messages);
        return messages.stream().findFirst().orElse("Hello, World!");
    }
}

package blockchain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.ThreadLocalRandom;

public class BlockchainUtils {

    public static final int NUMBER_OF_BLOCKS = 15;
    public static final int BLOCK_INIT_REWARD = 100;

    public static long generateMagicNumber() {
        final long max = 99999999;
        final long min = 10000000;
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static long generateAmount() {
        final long max = 100;
        final long min = 0;
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
}

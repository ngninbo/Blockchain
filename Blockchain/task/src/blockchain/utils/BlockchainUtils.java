package blockchain.utils;

import blockchain.domain.Block;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BlockchainUtils {

    public static final int NUMBER_OF_BLOCKS = 5;

    public static int getNumberOfHashStartingZeros() {
        System.out.print("Enter how many zeros the hash must start with: ");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

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

    /**
     * Validate chain of blocks
     * @param blocks List of Blocks
     * @return Whether blocks are chained with each other
     */
    public static boolean validate(List<Block> blocks) {

        return IntStream.range(0, NUMBER_OF_BLOCKS)
                .noneMatch(i -> i + 1 < NUMBER_OF_BLOCKS && !blocks.get(i).isChainedWith(blocks.get(i + 1)));
    }
}

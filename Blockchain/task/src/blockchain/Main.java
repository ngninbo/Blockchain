package blockchain;

import blockchain.domain.MessageSender;
import blockchain.model.Block;
import blockchain.domain.BlockMiner;
import blockchain.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static blockchain.utils.BlockchainUtils.NUMBER_OF_BLOCKS;

public class Main {

    private static final Blockchain blockchain = Blockchain.getInstance();

    public static void main(String[] args) throws ExecutionException, InterruptedException, NoSuchAlgorithmException {

        final int numberOfMiners = 10;
        int poolSize = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        List<MessageSender> senders = new ArrayList<>();

        List<String> names = List.of("Sarah", "Tom", "Nick");

        for (String name : names) {
            User user = new User(name);
            user.generateKeys();
            MessageSender sender = new MessageSender(user);
            senders.add(sender);
        }

        List<BlockMiner> miners = IntStream.rangeClosed(1, numberOfMiners)
                .mapToObj(BlockMiner::new)
                .collect(Collectors.toList());

        while (blockchain.size() < NUMBER_OF_BLOCKS) {
            senders.forEach(executorService::submit);
            Block block = executor.invokeAny(miners);
            blockchain.push(block);
            System.out.println(block);
        }

        executor.shutdown();
        executorService.shutdown();
    }
}

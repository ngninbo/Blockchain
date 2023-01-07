package blockchain;

import blockchain.domain.MessageSender;
import blockchain.model.Block;
import blockchain.domain.BlockMiner;
import blockchain.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static blockchain.utils.BlockchainUtils.NUMBER_OF_BLOCKS;

public class Main {

    private static final Blockchain blockchain = Blockchain.getInstance();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<MessageSender> senders = Stream.of("Sarah", "Tom", "Nick")
                .map(User::new)
                .map(MessageSender::new)
                .collect(Collectors.toList());

        int poolSize = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        final int numberOfMiners = 10;
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

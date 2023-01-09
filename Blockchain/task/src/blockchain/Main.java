package blockchain;

import blockchain.domain.BlockMiner;
import blockchain.domain.MessageSender;
import blockchain.model.Block;
import blockchain.model.User;

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

    private static final int NUMBER_OF_MINERS = 10;
    private static final Blockchain blockchain = Blockchain.getInstance();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int poolSize = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        List<MessageSender> senders = initSender();
        List<BlockMiner> miners = initMiners();

        while (blockchain.size() < NUMBER_OF_BLOCKS) {
            senders.forEach(executorService::submit);
            Block block = executor.invokeAny(miners);
            blockchain.push(block);
        }

        executor.shutdown();
        executorService.shutdown();

        blockchain.print();
    }

    private static List<MessageSender> initSender() {
        List<MessageSender> senders = new ArrayList<>();

        List<String> names = List.of("Sarah", "Tom", "Nick");

        names.stream()
                .map(name -> new User(name))
                .forEach(user -> {
                    blockchain.add(user);
                    senders.add(new MessageSender(user));
                });

        return senders;
    }

    private static List<BlockMiner> initMiners() {
        return IntStream.rangeClosed(1, NUMBER_OF_MINERS)
                .mapToObj(BlockMiner::new)
                .collect(Collectors.toList());
    }
}

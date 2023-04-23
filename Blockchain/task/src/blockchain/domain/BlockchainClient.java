package blockchain.domain;

import blockchain.Blockchain;
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

public class BlockchainClient extends Client {

    private static final int NUMBER_OF_MINERS = 10;
    private static final Blockchain blockchain = Blockchain.getInstance();

    private List<MessageSender> senders;
    private List<BlockMiner> miners;

    @Override
    protected void initSender() {
        senders = new ArrayList<>();

        List<String> names = List.of("Sarah", "Tom", "Nick", "Joe");

        names.stream()
                .map(name -> new User(name))
                .forEach(user -> {
                    blockchain.add(user);
                    senders.add(new MessageSender(user));
                });
    }

    @Override
    protected void initMiners() {
        miners = IntStream.rangeClosed(1, NUMBER_OF_MINERS)
                .mapToObj(BlockMiner::new)
                .collect(Collectors.toList());
    }

    @Override
    protected void submit() throws ExecutionException, InterruptedException {
        int poolSize = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        while (blockchain.size() < NUMBER_OF_BLOCKS) {
            senders.forEach(executorService::submit);
            Block block = executor.invokeAny(miners);
            blockchain.push(block);
        }

        executor.shutdown();
        executorService.shutdown();

        blockchain.print();
    }
}

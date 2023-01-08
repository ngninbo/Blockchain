package blockchain.formatter;

import java.util.Set;

public abstract class BlockchainFormatter<T> {

    public abstract String format(Set<T> t);
}

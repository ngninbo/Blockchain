package blockchain.formatter;

import blockchain.model.Transaction;

import java.util.Set;

public class TransactionFormatter {

    public static String format(Set<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> "\n" + transaction.description())
                .reduce((acc, data) -> acc + data)
                .orElse("No transactions");
    }
}

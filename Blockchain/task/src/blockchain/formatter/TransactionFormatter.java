package blockchain.formatter;

import blockchain.model.Transaction;
import blockchain.utils.ResourceProperties;

import java.text.MessageFormat;
import java.util.Set;

public class TransactionFormatter {

    private static final ResourceProperties PROPERTIES = ResourceProperties.getInstance();

    public static String format(Set<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionFormatter::format)
                .reduce((acc, data) -> acc + data)
                .orElse(PROPERTIES.get("blockchain_transaction_empty"));
    }

    private static String format(Transaction transaction) {
        return MessageFormat.format(PROPERTIES.get("blockchain_message_format"),
                transaction.getSender().getName(), transaction.getAmount(), transaction.getReceiver().getName());
    }
}

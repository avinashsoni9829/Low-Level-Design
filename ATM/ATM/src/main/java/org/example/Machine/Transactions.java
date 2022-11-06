package org.example.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    private Long transactionId;
    private LocalDateTime date_of_transaction;
    private TransactionType type;
    private int transaction_amount;
    private Verdict verdict;
    private Location location;

    public Transactions(TransactionType type, int amount, Verdict verdict, Location location) {
        date_of_transaction = LocalDateTime.now();
        this.transaction_amount = amount;
        this.verdict = verdict;
        this.location = location;
        this.type = type;
    }
}

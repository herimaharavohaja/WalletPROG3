package org.example.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransferHistory {
    private int Id;
    private int transactionDebitId;
    private int TransactionCreditId;
    private LocalDateTime dateTransfer;

    @Override
    public String toString() {
        return "TransferHistory{" +
                "Id=" + Id +
                ", transactionDebitId=" + transactionDebitId +
                ", TransactionCreditId=" + TransactionCreditId +
                ", dateTransfer=" + dateTransfer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferHistory that)) return false;
        return Id == that.Id && transactionDebitId == that.transactionDebitId && TransactionCreditId == that.TransactionCreditId && Objects.equals(dateTransfer, that.dateTransfer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, transactionDebitId, TransactionCreditId, dateTransfer);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTransactionDebitId() {
        return transactionDebitId;
    }

    public void setTransactionDebitId(int transactionDebitId) {
        this.transactionDebitId = transactionDebitId;
    }

    public int getTransactionCreditId() {
        return TransactionCreditId;
    }

    public void setTransactionCreditId(int transactionCreditId) {
        TransactionCreditId = transactionCreditId;
    }

    public LocalDateTime getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(LocalDateTime dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}

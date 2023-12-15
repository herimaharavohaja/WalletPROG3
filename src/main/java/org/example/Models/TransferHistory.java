package org.example.Models;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransferHistory {
    private Long transferHistoryId;
    private Account debitTransaction;
    private Account creditTransaction;
    private Double amount;
    private LocalDateTime transferDate;

    public TransferHistory(Long transferHistoryId, Account debitTransaction, Account creditTransaction, Double amount, LocalDateTime transferDate) {
        this.transferHistoryId = transferHistoryId;
        this.debitTransaction = debitTransaction;
        this.creditTransaction = creditTransaction;
        this.amount = amount;
        this.transferDate = transferDate;
    }

    public TransferHistory() {;
    }

    @Override
    public String toString() {
        return "TransferHistory{" +
                "transferHistoryId=" + transferHistoryId +
                ", debitTransaction=" + debitTransaction +
                ", creditTransaction=" + creditTransaction +
                ", amount=" + amount +
                ", transferDate=" + transferDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferHistory that)) return false;
        return Objects.equals(transferHistoryId, that.transferHistoryId) && Objects.equals(debitTransaction, that.debitTransaction) && Objects.equals(creditTransaction, that.creditTransaction) && Objects.equals(amount, that.amount) && Objects.equals(transferDate, that.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferHistoryId, debitTransaction, creditTransaction, amount, transferDate);
    }

    public Long getTransferHistoryId() {
        return transferHistoryId;
    }

    public void setTransferHistoryId(Long transferHistoryId) {
        this.transferHistoryId = transferHistoryId;
    }

    public Account getDebitTransaction() {
        return debitTransaction;
    }

    public void setDebitTransaction(Account debitTransaction) {
        this.debitTransaction = debitTransaction;
    }

    public Account getCreditTransaction() {
        return creditTransaction;
    }

    public void setCreditTransaction(Account creditTransaction) {
        this.creditTransaction = creditTransaction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }
}

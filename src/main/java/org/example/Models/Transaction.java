package org.example.Models;

import com.walletbyhei.model.type.TransactionType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private Long transactionId;
    private String label;
    private Double amount;
    private LocalDateTime dateTime;
    private TransactionType transactionType;
    private Account account;

    public Transaction(Long transactionId, String label, Double amount, LocalDateTime dateTime, TransactionType transactionType, Account account) {
        this.transactionId = transactionId;
        this.label = label;
        this.amount = amount;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", label='" + label + '\'' +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", transactionType=" + transactionType +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(label, that.label) && Objects.equals(amount, that.amount) && Objects.equals(dateTime, that.dateTime) && transactionType == that.transactionType && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, label, amount, dateTime, transactionType, account);
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

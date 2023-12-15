package org.example.Models;

import java.util.Currency;
import java.util.List;
import java.util.Objects;

public class Account {
    private Long accountId;
    private String accountName;
    private AccountType accountType;
    private List<Transaction> transactionList;
    private Balance balance;
    private Currency currency;

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountType=" + accountType +
                ", transactionList=" + transactionList +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(accountId, account.accountId) && Objects.equals(accountName, account.accountName) && accountType == account.accountType && Objects.equals(transactionList, account.transactionList) && Objects.equals(balance, account.balance) && Objects.equals(currency, account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountName, accountType, transactionList, balance, currency);
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

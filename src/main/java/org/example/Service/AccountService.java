package org.example.Service;

import org.example.Model.Account;
import org.example.Repository.AccountRepository;

import java.util.Date;
import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<List<Account>> getAllAccountsPagination(Date dateHeure) {
        return accountRepository.getAccountsPagination((java.sql.Date) dateHeure);
    }

    public List<Double> getBalanceHistoryForAccountInTimeInterval(int accountId, Date startDate, Date endDate) {
        return accountRepository.getBalanceHistoryForAccountInTimeInterval(accountId, (java.sql.Date) startDate, (java.sql.Date) endDate);
    }

    public Account getAccountById(int id) {
        return accountRepository.getAccountById(id);
    }

    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    public void createAccount(Account account) {
        accountRepository.createAccount(account);
    }
}

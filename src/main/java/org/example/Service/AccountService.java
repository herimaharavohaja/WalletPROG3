package org.example.Service;

import org.example.Model.Account;
import org.example.Repository.AccountRepository;

import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<List<Account>> getAllAccountsPagination() {
        return accountRepository.selectAllAccountsPagination();
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

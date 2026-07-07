package com.vti.service.impl;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository repository;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = repository.findAll();
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Account account = repository.findById(id).get();
        return account;
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void create(Account account) {
        repository.save(account);
    }

    @Override
    public void update(Account account, Integer id) {
        Account accountUpdate = repository.findById(id).orElse(null);
        if (Objects.isNull(accountUpdate)) {
            throw new RuntimeException("ID not found!");
        } else {
            accountUpdate.setUsername(account.getUsername());
            accountUpdate.setFullName(account.getFullName());
            accountUpdate.setEmail(account.getEmail());
            accountUpdate.setDep(account.getDep());

            repository.save(accountUpdate);
        }
    }
}

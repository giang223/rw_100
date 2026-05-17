package com.vti.backend.service.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.entity.Account;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    IAccountRepository repository = new AccountRepositoryImpl();
    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean create(String email, String username, String fullName, int depId, int posId) {
        return repository.create(email, username, fullName, depId, posId);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId)
    {
        return repository.update(id, updateName, email, username, departmentId, positionId);
    }
}

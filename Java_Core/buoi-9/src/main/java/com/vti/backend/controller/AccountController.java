package com.vti.backend.controller;

import com.vti.backend.service.IAccountService;
import com.vti.backend.service.impl.AccountServiceImpl;
import com.vti.entity.Account;

import java.util.List;

public class AccountController {
    IAccountService service = new AccountServiceImpl();

    public List<Account> findAll()
    {
        return service.findAll();
    }

    public boolean create(String email, String username, String fullName, int depId, int posId)
    {
        return service.create(email, username, fullName, depId, posId);
    }

    public boolean delete(int id)
    {
        return service.delete(id);
    }

    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId)
    {
        return service.update(id, updateName, email, username, departmentId, positionId);
    }

    public boolean checkUsernameExist(String username, Integer id) {
        return service.checkUsernameExist(username, id);
    }

    public boolean checkEmailExist(String email) {
        return service.checkEmailExist(email);
    }

    public boolean checkExistID(int id)
    {
        return service.checkExistID(id);
    }

    public String importAccountFromCSV(String pathName) {return service.importAccountFromCSV(pathName);}
}

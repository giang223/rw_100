package com.vti.backend.service;

import com.vti.dto.context.AccountContext;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;
import com.vti.entity.Department;

import java.util.List;
import java.util.Map;

public interface IAccountService extends ImportFileCSV<AccountContext, Account, AccountCsv> {
    List<Account> findAll();
    boolean create( String username, String fullName, String email, int depId, int posId);
    boolean delete(int id);
    boolean update(int id, String username, String fullName, String email, int departmentId, int positionId);
    boolean checkExistID(int id);
    boolean checkUsernameExist(String username,  Integer id);
    boolean checkEmailExist(String email);

    String importAccountFromCSV(String pathName);
}

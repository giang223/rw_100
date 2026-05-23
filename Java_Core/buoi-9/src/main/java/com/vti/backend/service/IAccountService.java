package com.vti.backend.service;

import com.vti.entity.Account;
import com.vti.entity.Department;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    boolean create( String username, String fullName, String email, int depId, int posId);
    boolean delete(int id);
    boolean update(int id, String username, String fullName, String email, int departmentId, int positionId);
    boolean checkExistID(int id);
    boolean checkUsernameExist(String username,  Integer id);
    boolean checkEmailExist(String email);

    String importAccountFromCSV(String pathName);
}

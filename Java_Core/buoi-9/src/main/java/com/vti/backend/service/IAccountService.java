package com.vti.backend.service;

import com.vti.entity.Account;
import com.vti.entity.Department;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    boolean create( String username, String fullName, String email, int depId, int posId);
    boolean delete(int id);
    boolean update(int id, String username, String fullName, String email, int departmentId, int positionId);
    boolean checkExistUsernameOrEmailAndIdNot(String username, String email, Integer id);
    boolean checkExistID(int id);
}

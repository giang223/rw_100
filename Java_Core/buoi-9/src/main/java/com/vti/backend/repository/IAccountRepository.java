package com.vti.backend.repository;

import com.vti.entity.Account;
import com.vti.entity.Department;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    boolean create( String username, String fullName, String email, int depId, int posId);
    boolean delete(int id);
    boolean update(int id, String username, String fullName, String email, int departmentId, int positionId);

    public boolean checkExistUsernameOrEmailAndIdNot(String username, String email, Integer id);
    public boolean checkExistID(int id);
}

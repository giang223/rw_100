package com.vti.backend.repository;

import com.vti.entity.Account;
import com.vti.entity.Department;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    boolean create(String email, String username, String fullName, int depId, int posId);
    boolean delete(int id);
    boolean update(int id, String updateName, String email, String username, int departmentId, int positionId);
}

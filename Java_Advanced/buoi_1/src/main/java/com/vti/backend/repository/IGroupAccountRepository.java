package com.vti.backend.repository;

import com.vti.entity.Account;
import com.vti.entity.GroupAccount;

import java.util.List;

public interface IGroupAccountRepository {
    List<GroupAccount> findAll();
    GroupAccount findById(Integer id);
    void create(GroupAccount groupAccount);
    void delete(Integer id);
}

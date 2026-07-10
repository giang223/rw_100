package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    List<Account> findByFullName(String fullName);
    List<Account> findAllByFullName(String fullName);
    Account findByFullNameAndUsername(String fullName, String username);
    List<Account> findByFullNameOrUsername(String fullName, String username);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Integer id);
    boolean existsByEmailAndIdNot(String email, Integer id);
}

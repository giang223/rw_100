package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.form.AccountCreateOrUpdateForm;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();
    AccountDTO findByUsername(String username);
    List<AccountDTO> findAllByFullName(String fullName);
    AccountDTO findByFullNameAndUsername(String fullName, String username);
    List<AccountDTO> findByFullNameOrUsername(String fullName, String username);
    AccountDTO findById(Integer id);
    void deleteById(Integer id);
    void create(AccountCreateOrUpdateForm form);
    void update(AccountCreateOrUpdateForm form, Integer id);
}

package com.vti.service.impl;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        AccountDTO dto = null;
        if (Objects.nonNull(account)) {
            dto = modelMapper.map(account, AccountDTO.class);
        }
        return dto;
    }

    @Override
    public List<AccountDTO> findAllByFullName(String fullName) {
        List<Account> accounts = accountRepository.findAllByFullName(fullName);
        List<AccountDTO> dtos = null;
        if (Objects.nonNull(accounts)) {
            dtos = accounts.stream().map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
        }
        return dtos;
    }

    @Override
    public AccountDTO findByFullNameAndUsername(String fullName, String username) {
        Account account = accountRepository.findByFullNameAndUsername(fullName, username);
        AccountDTO dto = null;
        if (Objects.nonNull(account)) {
            dto = modelMapper.map(account, AccountDTO.class);
        }
        return dto;
    }

    @Override
    public List<AccountDTO> findByFullNameOrUsername(String fullName, String username) {
        List<Account> accounts = accountRepository.findByFullNameOrUsername(fullName, username);
        List<AccountDTO> dtos = null;
        if (Objects.nonNull(accounts)) {
            dtos = accounts.stream().map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
        }
        return dtos;
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id).get();
        AccountDTO dto = null;
        if(Objects.nonNull(account))
        {
            dto = modelMapper.map(account, AccountDTO.class);
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void create(AccountCreateOrUpdateForm form) {
        if(accountRepository.existsByEmailAndIdNot(form.getEmail(), null))
        {
            throw new RuntimeException("Username Exists");
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), null)) {
            throw new RuntimeException("Email exists");
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found"));

        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setEmail(form.getEmail());
        account.setDepartment(department);
        account.setPosition(position);

        accountRepository.save(account);
    }

    @Override
    public void update(AccountCreateOrUpdateForm form, Integer id) {
        Account accountUpdate = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), id)) {
            throw new RuntimeException("Username exists");
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), id)) {
            throw new RuntimeException("Email exists");
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found"));

        accountUpdate.setUsername(form.getUsername());
        accountUpdate.setEmail(form.getEmail());
        accountUpdate.setFullName(form.getFullName());
        accountUpdate.setDepartment(department);
        accountUpdate.setPosition(position);

        accountRepository.save(accountUpdate);
    }
}

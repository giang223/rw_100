package com.vti.backend.service.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements IAccountService {
    IAccountRepository repository = new AccountRepositoryImpl();
    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean create( String username, String fullName, String email, int depId, int posId) {
        return repository.create(email, username, fullName, depId, posId);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(int id, String username, String fullName, String email, int departmentId, int positionId)
    {
        return repository.update(id, username, fullName, email, departmentId, positionId);
    }
    @Override
    public boolean checkUsernameExist(String username,  Integer id) {
        return repository.checkUsernameExist(username, id);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return repository.checkEmailExist(email);
    }

    @Override
    public String importAccountFromCSV(String pathName) {
        if (!pathName.endsWith(".csv")) {
            return "Định dạng file không đúng";
        }

        List<Account> accounts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathName)))
        {
            br.readLine();
            String line;
            while((line = br.readLine()) != null)
            {
                String[] fields = line.split(",");
                String username = fields[0];
                String fullName = fields[1];
                String email = fields[2];

                Account account = new Account(username, fullName, email, new Department(), new Position(), LocalDate.now());
                account.getDepartment().setId(Integer.parseInt(fields[3]));
                account.getPosition().setId(Integer.parseInt(fields[4]));

                accounts.add(account);
            }
            repository.createListAccount(accounts);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "Import thành công ";
    }

    @Override
    public boolean checkExistID(int id) {
        return repository.checkExistID(id);
    }
}

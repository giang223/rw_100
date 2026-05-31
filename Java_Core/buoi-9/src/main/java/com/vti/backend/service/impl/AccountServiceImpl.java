package com.vti.backend.service.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.dto.ImportError;
import com.vti.dto.context.AccountContext;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements IAccountService {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+$";

    IAccountRepository accountRepository = new AccountRepositoryImpl();
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    IPositionRepository positionRepository =  new PositionRepositoryImpl();
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create( String username, String fullName, String email, int depId, int posId) {
        return accountRepository.create(email, username, fullName, depId, posId);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public boolean update(int id, String username, String fullName, String email, int departmentId, int positionId)
    {
        return accountRepository.update(id, username, fullName, email, departmentId, positionId);
    }
    @Override
    public boolean checkUsernameExist(String username,  Integer id) {
        return accountRepository.checkUsernameExist(username, id);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return accountRepository.checkEmailExist(email);
    }

    @Override
    public boolean checkExistID(int id) {
        return accountRepository.checkExistID(id);
    }

    @Override
    public String importAccountFromCSV(String pathName) {

        String pathError = "src/main/java/com/vti/dto/output_error_account.csv";

        Map<String, Account> mapByUsername = accountRepository.mapByUsername();
        Map<String, Account> mapByEmail = accountRepository.mapByEmail();
        List<Department> departments = departmentRepository.findAll();
        List<Position> positions = positionRepository.findAll();
        AccountContext context = new AccountContext(mapByUsername, mapByEmail, departments, positions);

        String message = this.importFileCSV(pathName, context, pathError);
        return message;
    }

    @Override
    public void exportFileError(List<ImportError<AccountCsv>> importErrors, String pathError)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathError));
            bw.write("username,fullName,email,department_id,position_id,error_message");
            bw.newLine();
            for (ImportError error : importErrors) {
                String ln = error.getCsv() + " : " + String.join("|", error.getMessage());
                bw.write(ln);
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validation(String line, AccountContext context, List<Account> entities, List<ImportError<AccountCsv>> importErrors)
    {
        List<String> errors = new ArrayList<>();

        String[] fields = line.split(",", -1);
        if (fields.length < 5) {
            errors.add("Dòng dữ liệu bị lỗi cấu trúc!");
            importErrors.add(new ImportError(line, errors));
            return;
        }
        String username = fields[0].trim();
        String fullName = fields[1].trim();
        String email = fields[2].trim();
        String departmentId = fields[3].trim();
        String positionId = fields[4].trim();

        // Validation username
        if (username.isEmpty()) {
            errors.add("Username không được để trống");
        } else if (username.length() > 100) {
            errors.add("Username không được dài quá 100 ký tự");
        } else if (context.getMapByUsername().get(username) != null) {
            errors.add("Username đã tồn tại trên hệ thống");
        }

        // Validation fullName
        if (fullName.isEmpty()) {
            errors.add("Full name không được để trống");
        }

        // Validation email
        if (email.isEmpty()) {
            errors.add("Email không được để trống");
        }else if (username.length() > 100) {
            errors.add("Username không được dài quá 100 ký tự");
        } else if(!email.matches(EMAIL_REGEX)) {
            errors.add("Định dạng email không hợp lệ");
        } else if (context.getMapByEmail().get(email) != null) {
            errors.add("Email đã tồn tại trên hệ thống");
        }

        // Validation Department
        Department department = null;
        if(departmentId.isEmpty())
        {
            errors.add("Department ID không được để trống");
        }
        else {
            try {
                for (Department de : context.getDepartments()) {
                    if (de.getId() == Integer.parseInt(departmentId)) {
                        department = de;
                        break;
                    }
                }
                if(department == null)
                {
                    errors.add("Không tìm thấy Phòng ban nào có ID này ");
                }
            } catch (NumberFormatException e) {
                errors.add("Department ID phải là một số nguyên");            }
        }

        // Validation position
        Position position = null;
        if(positionId.isEmpty())
        {
            errors.add("Position ID không được để trống");
        }
        else {
            try {
                for (Position po : context.getPositions()) {
                    if (po.getId() == Integer.parseInt(positionId)) {
                        position = po;
                        break;
                    }
                }
                if(position == null)
                {
                    errors.add("Không tìm thấy Chức vụ nào có ID này " );
                }
            } catch (NumberFormatException e) {
                errors.add("Position ID phải là một số nguyên");            }
        }

        if(errors.isEmpty())
        {
            Account account = new Account(username, fullName, email, department, position);
            entities.add(account);
            context.getMapByUsername().put(username, account);
            context.getMapByEmail().put(email, account);
        }
        else {
            ImportError importerError = new ImportError(line, errors);
            importErrors.add(importerError);
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountRepository.createListAccount(entities);
    }

}

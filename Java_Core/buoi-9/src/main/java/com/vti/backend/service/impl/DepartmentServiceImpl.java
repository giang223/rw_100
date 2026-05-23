package com.vti.backend.service.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.service.IDepartmentService;
import com.vti.entity.Department;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository repository = new DepartmentRepositoryImpl();

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean create(String name) {
        return repository.create(name);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(int id, String name) {
        return repository.update(id, name);
    }

    @Override
    public boolean checkExistNameAndIdNot(String name, Integer id) {
        return repository.checkExistNameAndIdNot(name, id);
    }

    @Override
    public boolean checkExistID(Integer id) {
        return repository.checkExistID(id);
    }

    @Override
    public String importDepartmentFromCSV(String pathName) {
        if (!pathName.endsWith(".csv")) {
            return "Định dạng file không đúng";
        }

        boolean checkCreate = false;
        List<Department> departments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String departmentName = fields[0];
                // validation
                Department dep = new Department(departmentName);
                departments.add(dep);
            }
            checkCreate = repository.createListDepartment(departments);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Import thành công ";
    }
}

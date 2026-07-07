package com.vti.service.impl;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentRepository repository;

    @Override
    public List<Department> findAll() {
        List<Department> departments = repository.findAll();
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = repository.findById(id).get();
        return department;
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void create(Department department) {
        repository.save(department);
    }

    @Override
    public void update(Department department, Integer id) {
        Department departmentUpdate = repository.findById(id).orElse(null);
        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID not found!");
        } else {
            departmentUpdate.setName(department.getName());
            repository.save(departmentUpdate);
        }
    }
}

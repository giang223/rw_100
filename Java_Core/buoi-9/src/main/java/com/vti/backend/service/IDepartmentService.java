package com.vti.backend.service;

import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();
    boolean create(String name);
    boolean delete(int id);
    boolean update(int id, String name);
}

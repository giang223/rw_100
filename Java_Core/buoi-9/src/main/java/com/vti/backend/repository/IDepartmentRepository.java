package com.vti.backend.repository;

import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    boolean create(String name);
    boolean delete(int id);
    boolean update(int id, String name);

    boolean checkExistID(Integer id);
    boolean checkExistNameAndIdNot(String name, Integer id);

    boolean createListDepartment(List<Department> list);
}

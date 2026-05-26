package com.vti.backend.repository;

import com.vti.entity.Department;

import java.util.List;
import java.util.Map;

public interface IDepartmentRepository {
    List<Department> findAll();
    Map<String, Department> mapByName();
    boolean create(String name);
    boolean delete(int id);
    boolean update(int id, String name);

    boolean checkExistID(Integer id);
    boolean checkExistNameAndIdNot(String name, Integer id);

    boolean createListDepartment(List<Department> list);
}

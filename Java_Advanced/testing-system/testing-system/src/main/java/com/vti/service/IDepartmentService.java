package com.vti.service;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentCreateOrUpdateForm;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();

    DepartmentDTO findById(Integer id);
    DepartmentDTO findByName(String name);

    void deleteById(Integer id);

    void create(DepartmentCreateOrUpdateForm department);
    void update(DepartmentCreateOrUpdateForm department, Integer id);

}

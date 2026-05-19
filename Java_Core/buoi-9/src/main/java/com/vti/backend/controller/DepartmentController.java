package com.vti.backend.controller;

import com.vti.backend.service.IDepartmentService;
import com.vti.backend.service.impl.DepartmentServiceImpl;
import com.vti.entity.Department;

import java.util.List;

public class DepartmentController {

    private IDepartmentService service = new DepartmentServiceImpl();

    public List<Department> findAll()
    {
        List<Department> departments = service.findAll();
        return departments;
    }

    public boolean create(String name)
    {
        return service.create(name);
    }

    public boolean delete(int id)
    {
        return service.delete(id);
    }

    public boolean update(int id, String name)
    {
        return service.update(id, name);
    }

    public boolean checkExistNameAndIdNot(String name, Integer id) {
        return service.checkExistNameAndIdNot(name, id);
    }

    public boolean checkExistID(Integer id) {
        return service.checkExistID(id);
    }
}

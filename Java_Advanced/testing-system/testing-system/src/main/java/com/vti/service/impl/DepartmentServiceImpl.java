package com.vti.service.impl;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentCreateOrUpdateForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Department> findAll() {
        List<Department> departments = repository.findAll();
        return departments;
    }

    @Override
    public DepartmentDTO findById(Integer id) {
        Department department = repository.findById(id).get();
        DepartmentDTO dto = null;
        if(Objects.nonNull(department))
        {
            dto = modelMapper.map(department, DepartmentDTO.class);
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void create(DepartmentCreateOrUpdateForm form)
    {
        if(repository.existsByNameAndIdNot(form.getName(), null))
        {
            throw new RuntimeException("Department name exists");
        }

        Department department = new Department();
        department.setName(form.getName());

        repository.save(department);
    }

    @Override
    public void update(DepartmentCreateOrUpdateForm form, Integer id) {
        Department departmentUpdate = repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));

        departmentUpdate.setName(form.getName());

        repository.save(departmentUpdate);
    }

    @Override
    public DepartmentDTO findByName(String name) {
        Department department = repository.findByName(name);
        DepartmentDTO dto = null;
        if(Objects.nonNull(department))
        {
            dto = modelMapper.map(department, DepartmentDTO.class);
        }
        return dto;
    }
}

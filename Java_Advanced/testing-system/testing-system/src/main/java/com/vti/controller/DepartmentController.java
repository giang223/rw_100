package com.vti.controller;

import com.vti.entity.Department;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{idSearch}")
    public ResponseEntity<Department> findById(@PathVariable(name = "idSearch") Integer id)
    {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/{idDelete}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Department department) {
        departmentService.create(department);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody Department department,
                                         @PathVariable(name = "idUpdate") Integer id) {
        departmentService.update(department, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
}

package com.vti.backend.service.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.service.IDepartmentService;
import com.vti.dto.ImportError;
import com.vti.dto.context.DepartmentContext;
import com.vti.dto.csv.DepartmentCsv;
import com.vti.entity.Department;

import java.io.*;
import java.util.*;

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
        String pathError = "src/main/java/com/vti/dto/output_error_department.csv";

        Map<String, Department> mapByName = repository.mapByName();
        DepartmentContext context = new DepartmentContext(mapByName);
        if (mapByName != null) {
            cleanMap(mapByName);
        }
        String message = this.importFileCSV(pathName, context, pathError);

        return message;
    }

    private void cleanMap(Map<String, Department> map)
    {
        Map<String, Department> updatedEntries = new HashMap<>();

        for (Map.Entry<String, Department> entry : map.entrySet()) {
            String rawKey = entry.getKey();
            Department dep = entry.getValue();

            dep.setName(java.text.Normalizer.normalize(dep.getName().trim(), java.text.Normalizer.Form.NFC));

            String cleanKey = java.text.Normalizer.normalize(rawKey.trim(), java.text.Normalizer.Form.NFC);

            if (!rawKey.equals(cleanKey)) {
                updatedEntries.put(cleanKey, dep);
            }
        }

        map.putAll(updatedEntries);
    }

    @Override
    public void exportFileError(List<ImportError<DepartmentCsv>> importErrors, String pathError)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathError));
            bw.write("department_name,error_message");
            bw.newLine();

            for (ImportError error : importErrors) {
                String ln = error.getCsv().toString() + "," + String.join("|", error.getMessage());
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
    public void validation(String line, DepartmentContext context, List<Department> entities, List<ImportError<DepartmentCsv>> importErrors)
    {
        List<String> errors = new ArrayList<>();

        String[] fields = line.split(",", -1);
        String departmentName = fields[0];

        // 1. Validation
        if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()) {
            errors.add("Tên phòng ban ko được để trống");
        } else if (departmentName.length() > 100) {
            errors.add("Tên phòng ban ko được dài quá 100 kí tự");
        } else if (context.getMapByName().get(departmentName) != null) {
            errors.add("Tên phòng ban đã tồn tại");
        }

        if (errors.isEmpty()) {
            Department dep = new Department(departmentName);
            entities.add(dep);
            context.getMapByName().put(departmentName, dep);
        } else {
            DepartmentCsv csv = new DepartmentCsv(departmentName);
            ImportError importError = new ImportError(csv, errors);
            importErrors.add(importError);
        }
    }

    @Override
    public void saveAll(List<Department> entities) {
        repository.createListDepartment(entities);
    }
}

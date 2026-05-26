package com.vti.backend.service.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.service.IDepartmentService;
import com.vti.dto.ImportError;
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
        if (!pathName.endsWith(".csv")) {
            return "Định dạng file không đúng";
        }

        Map<String, Department> mapByName = repository.mapByName();
        List<Department> departments = new ArrayList<>();
        List<ImportError> importErrors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                validation(line, mapByName, departments, importErrors);
            }

            if (!departments.isEmpty()) {
                repository.createListDepartment(departments);
            }

            if (!importErrors.isEmpty()) {
                String pathError = "src/main/java/com/vti/dto/output_error_department.csv";
                exportFileCSV(importErrors, pathError);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = "";
        if (importErrors.isEmpty()) {
            message = "Import thành công";
        } else if (departments.isEmpty()) {
            message = "Import ko thành công, đã xuất file lỗi tại src/main/java/com/vti/dto/output_error_department.csv";
        } else {
            message = "Import thành công " + departments.size() + " phòng ban, " +
                    "đã xuất lỗi ra file tại src/main/java/com/vti/dto/output_error_department.csv";
        }

        return message;
    }

    private void exportFileCSV(List<ImportError> importErrors, String pathError)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathError));
            bw.write("department_name,error_message");
            bw.newLine();

            for (ImportError error : importErrors) {
                String ln = error.getLine() + "," + String.join("|", error.getMessage());
                bw.write(ln);
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validation(String line, Map<String, Department> mapByName, List<Department> departments, List<ImportError> importErrors)
    {
        List<String> errors = new ArrayList<>();

        String[] fields = line.split(",");
        String departmentName = fields[0];

        // 1. Validation
        if (Objects.isNull(departmentName) || departmentName.trim().isEmpty()) {
            errors.add("Tên phòng ban ko được để trống");
        } else if (departmentName.length() > 100) {
            errors.add("Tên phòng ban ko được dài quá 100 kí tự");
        } else if (mapByName.get(departmentName) != null) {
            errors.add("Tên phòng ban đã tồn tại");
        }

        if (errors.isEmpty()) {
            Department dep = new Department(departmentName);
            departments.add(dep);
            mapByName.put(departmentName, dep);
        } else {
            ImportError importerError = new ImportError(line, errors);
            importErrors.add(importerError);
        }
    }
}

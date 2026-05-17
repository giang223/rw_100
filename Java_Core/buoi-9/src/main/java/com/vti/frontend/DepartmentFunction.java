package com.vti.frontend;

import com.vti.backend.controller.DepartmentController;
import com.vti.entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentFunction {

    private static Scanner scanner = new Scanner(System.in);
    private DepartmentController departmentController = new DepartmentController();

    public void run(){
        while (true) {
            System.out.println("=== Mời bạn chọn chức năng ===");
            System.out.println("1. Xem ds phòng ban");
            System.out.println("2. Thêm mới phòng ban");
            System.out.println("3. Update phòng ban");
            System.out.println("4. Xóa phòng ban");
            System.out.println("5. Tìm kiếm phòng ban");
            System.out.println("6. Thoát");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Department> departments = departmentController.findAll();
                    showDepartment(departments);
                    break;
                case "2":
                    insertDepartment();
                    break;
                case "3":
                    updateDepartment();
                    break;
                case "4":
                    deleteDepartment();
                    break;
                default:
                    System.out.println("Chọn sai, chọn lại!");

            }
        }
    }

    public void showDepartment(List<Department> departments) {
        System.out.println("+-----+--------------------+");
        System.out.printf("|%5s|%20s|\n", "ID", "Tên phòng ban");
        System.out.println("+-----+--------------------+");
        for (Department department : departments) {
            System.out.printf("|%5s|%20s|\n", department.getId(), department.getName());
        }
        if (departments.size() == 0) {
            System.out.println("Không tìm thấy");
        }
        System.out.println("+-----+--------------------+");
    }

    public void insertDepartment() {
        System.out.println("Nhập tên phòng ban: ");
        String name = scanner.nextLine();
        boolean check = departmentController.create(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm không thành công");
        }
    }

    public void deleteDepartment() {
        System.out.println("Nhập id phòng ban cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean check = departmentController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa không thành công");
        }
    }

    public void updateDepartment() {
        System.out.println("Nhập ID phòng ban cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên phòng ban cần sửa: ");
        String name = scanner.nextLine();
        boolean check = departmentController.update(id, name);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update không thành công");
        }
    }
}

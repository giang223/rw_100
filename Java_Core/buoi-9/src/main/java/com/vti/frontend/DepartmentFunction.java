package com.vti.frontend;

import com.vti.backend.controller.DepartmentController;
import com.vti.entity.Department;

import java.util.List;
import java.util.Objects;
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
        String name = "";
        System.out.println("Nhập tên phòng ban: ");
        while (true) {
            name = scanner.nextLine();
            // kiem tra xem ten có bị null hay empty hay ko
            if (Objects.isNull(name) || name.trim().isEmpty()) {
                System.out.println("Nhập lại tên: ");
                continue;
            }
            else {
                // kiem tra xem ten da ton tai chua
                if (departmentController.checkExistNameAndIdNot(name, null)) {
                    System.out.println("Tên này đã được sử dụng, Nhập lại: ");
                    continue;
                }
                break;
            }
        }

        boolean check = departmentController.create(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        }
    }

    public void deleteDepartment() {
        System.out.println("Nhập id phòng ban cần xóa: ");
        int id;
        while (true) {
            id = scanner.nextInt();
            scanner.nextLine();
            // kiem tra xem id co ton tai ko
            if (id <= 0 ) {
                System.out.println("Nhập lai ID: ");
                continue;
            }
            if (!departmentController.checkExistID(id)) {
                System.out.println("ID nay ko ton tai, nhap lai: ");
                continue;
            }
            break;
        }
        boolean check = departmentController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        }
    }

    public void updateDepartment() {
        System.out.println("Nhập ID phòng ban cần sửa: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên phòng ban cần sửa: ");
        String name= "";
        while (true) {
            name = scanner.nextLine();
            // kiem tra xem ten có bị null hay empty hay ko
            if (Objects.isNull(name) || name.trim().isEmpty()) {
                System.out.println("Nhập lại tên: ");
                continue;
            } else {
                // kiem tra xem ten da ton tai chua
                if (departmentController.checkExistNameAndIdNot(name, id)) {
                    System.out.println("Tên này đã được sử dụng, Nhập lại: ");
                    continue;
                }
                break;
            }
        }
        boolean check = departmentController.update(id, name);
        if (check) {
            System.out.println("Update thành công");
        }
    }

}

package com.vti.frontend;

import com.vti.backend.QLDepartment;
import com.vti.entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentFunction {
    private static Scanner scanner = new Scanner(System.in);

    public static void run(){
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
                    List<Department> departments = QLDepartment.findAllDepartment();
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
                case "5":
                    findDepartmentByNameAndId();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Chọn sai, chọn lại!");

            }
        }
    }

    public static void findDepartmentByNameAndId() {
        System.out.println("Nhập ID cần tìm: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên phòng ban cần tìm: ");
        String name = scanner.nextLine();
        java.util.List<Department> departments = QLDepartment.findByNameAndId(name, id);
        showDepartment(departments);
    }

    public static void updateDepartment() {
        System.out.println("Nhập ID phòng ban cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên phòng ban cần sửa: ");
        String name = scanner.nextLine();
        boolean check = QLDepartment.updateDepartment(id, name);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update không thành công");
        }
    }

    public static void deleteDepartment() {
        System.out.println("Nhập tên phòng ban cần xóa: ");
        String name = scanner.nextLine();
        boolean check = QLDepartment.deleteDepartment(name);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa không thành công");
        }
    }

    public static void insertDepartment() {
        System.out.println("Nhập tên phòng ban: ");
        String name = scanner.nextLine();
        boolean check = QLDepartment.createDepartment(name);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm không thành công");
        }
    }

    public static void showDepartment(List<Department> departments) {
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
}
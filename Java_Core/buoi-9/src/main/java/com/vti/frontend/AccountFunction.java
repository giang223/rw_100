package com.vti.frontend;

import com.vti.backend.controller.AccountController;
import com.vti.backend.controller.DepartmentController;
import com.vti.backend.controller.PositionController;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private static Scanner scanner = new Scanner(System.in);
    private AccountController accountController = new AccountController();
    private DepartmentController departmentController = new DepartmentController();
    private PositionController positionController = new PositionController();

    public void run()
    {
        System.out.println("\n========= QUẢN LÝ TÀI KHOẢN (ACCOUNT) =========");
        System.out.println("| 1. Xem danh sách tài khoản                  |");
        System.out.println("| 2. Thêm mới tài khoản                       |");
        System.out.println("| 3. Cập nhật Phòng ban/Chức vụ (theo ID)     |");
        System.out.println("| 4. Xóa tài khoản (theo Username)            |");
        System.out.println("| 5. Thoát                                    |");
        System.out.println("===============================================");
        System.out.print("Mời bạn chọn chức năng (1-7): ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                List<Account> accounts = accountController.findAll();
                showAccounts(accounts);
                break;
            case "2":
                insertAccount();
                break;
            case "3":
                updateAccount();
                break;
            case "4":
                deleteAccount();
                break;
            case "5":
                System.out.println("Thoát!");
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
        }
    }

    public void showAccounts(List<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("Danh sách tài khoản đang trống!");
            return;
        }
        System.out.println("\n+------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                "ID", "Email", "Username", "Full Name", "Department", "Position");
        System.out.println("+------+----------------------+----------------------+----------------------+----------------------+----------------------+");

        for (Account acc : accounts) {
            System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    acc.getId(),
                    acc.getEmail(),
                    acc.getUsername(),
                    acc.getFullName(),
                    (acc.getDepartment() != null ? acc.getDepartment().getName() : "N/A"),
                    (acc.getPosition() != null ? acc.getPosition().getName() : "N/A")
            );
        }
        System.out.println("+------+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }

    public void insertAccount()
    {
        System.out.println("--- Nhập thông tin tài khoản mới ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.println("Department ID: ");
        int deptID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Position ID: ");
        int posID = scanner.nextInt();
        scanner.nextLine();

        if (accountController.create(email, username, fullName, deptID, posID)) {
            System.out.println("Thêm tài khoản thành công!");
        } else {
            System.out.println("Thêm thất bại! Vui lòng kiểm tra lại dữ liệu.");
        }
    }

    public void deleteAccount()
    {
        System.out.println("Nhập ID của tài khoản cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean check = accountController.delete(id);
        if (check) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa thất bại!");
        }
    }

    public void updateAccount()
    {
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Nhập username: ");
        String username = scanner.nextLine();
        System.out.println("Nhập fullName: ");
        String fullName = scanner.nextLine();
        System.out.println("Chọn ID department: ");

        List<Department> departments = departmentController.findAll();
        String depID;
        while (true) {
            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", DepartmentName: " + department.getName());
            }
            depID = scanner.nextLine();
            boolean checkExists = checkExistDepartment(departments, depID);
            if (!checkExists) {
                System.out.println("Chọn sai, chọn lại!");
            } else {
                break;
            }
        }

        System.out.println("Chọn ID position: ");
        List<Position> positions = positionController.findAll();
        String poID;
        while (true) {
            for (Position position : positions) {
                System.out.println("ID: " + position.getId() + ", PositionName: " + position.getName());
            }
            poID = scanner.nextLine();
            boolean checkExists = checkExistPosition(positions, poID);
            if (!checkExists) {
                System.out.println("Chọn sai, chọn lại!");
            } else {
                break;
            }
        }
    }

    public static boolean checkExistDepartment(List<Department> departments, String id) {
        for (Department department : departments) {
            if (id.equals(String.valueOf(department.getId()))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkExistPosition(List<Position> positions, String id) {
        for (Position position : positions) {
            if (id.equals(String.valueOf(position.getId()))) {
                return true;
            }
        }
        return false;
    }
}

package com.vti.frontend;

import com.vti.backend.controller.AccountController;
import com.vti.backend.controller.DepartmentController;
import com.vti.backend.controller.PositionController;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.utils.ScannerUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AccountFunction {
    private static Scanner scanner = new Scanner(System.in);
    private AccountController accountController = new AccountController();
    private DepartmentController departmentController = new DepartmentController();
    private PositionController positionController = new PositionController();

    public void run()
    {
        while(true) {
            System.out.println("\n========= QUẢN LÝ TÀI KHOẢN (ACCOUNT) =========");
            System.out.println("| 1. Xem danh sách tài khoản                  |");
            System.out.println("| 2. Thêm mới tài khoản                       |");
            System.out.println("| 3. Cập nhật Phòng ban/Chức vụ (theo ID)     |");
            System.out.println("| 4. Xóa tài khoản (theo Username)            |");
            System.out.println("  5. Import file CSV                          |");
            System.out.println("| 6. Thoát                                    |");
            System.out.println("===============================================");
            System.out.print("Mời bạn chọn chức năng (1-6): ");
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
                    importAccountFromCSV();
                    break;
                case "6":
                    System.out.println("Thoát!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private void importAccountFromCSV() {
        System.out.println("=== Import file CSV ===");
        System.out.println("Mời bạn nhập đường dẫn đến file:");

        String pathName = scanner.nextLine();
        String message = accountController.importAccountFromCSV(pathName);
        System.out.println(message);
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
        String fullName;
        String username;
        String email;

        System.out.println("Nhập email: ");
        while (true) {
            email = ScannerUtils.inputEmail();
            // check trung
            if (accountController.checkEmailExist(email)) {
                System.out.println("email đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        // validation username
        System.out.println("Nhập username: ");
        while (true) {
            username = ScannerUtils.inputString();
            // check trung
            if (accountController.checkUsernameExist(username, null)) {
                System.out.println("Username đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        // validation fullName
        System.out.println("Nhập fullName: ");
        fullName = ScannerUtils.inputString();

        System.out.println("Chọn ID department: ");
        List<Department> departments = departmentController.findAll();
        Integer depID;
        while (true) {
            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", DepartmentName: " + department.getName());
            }
            depID = ScannerUtils.inputIntGreaterThenZero();
            // check departmentID có tồn tại ko
            boolean checkExists = departmentController.checkExistID(depID);//Integer.valueOf("abc")
            if (!checkExists) {
                System.out.println("Không ton tại deparmentID này:");

            } else {
                break;
            }
        }

        System.out.println("Chọn ID position: ");
        List<Position> positions = positionController.findAll();
        Integer poID;
        while (true) {
            for (Position position : positions) {
                System.out.println("ID: " + position.getId() + ", PositionName: " + position.getName());
            }

            poID = ScannerUtils.inputIntGreaterThenZero();
            // check positionID có tồn tại ko
            boolean checkExists = checkExistPosition(positions, String.valueOf(poID));
            if (!checkExists) {
                System.out.println("Không ton tại positionID này:");
            } else {
                break;
            }
        }
        boolean check = accountController.create(email, username, fullName, depID, poID);
        if (check) {
            System.out.println("Thêm mới thành công");
        } else {
            System.out.println("Thêm mới thất bại");
        }
    }

    public void deleteAccount()
    {
        int id;
        System.out.println("Nhập ID cần xóa: ");
        while (true) {
            id = ScannerUtils.inputIntGreaterThenZero();
            // kiem tra xem id nay co ton tai ko
            if (!accountController.checkExistID(id)) {
                System.out.println(" ID này không ton tai. Nhap lai: ");
            } else {
                break;
            }
        }

        boolean check = accountController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    public void updateAccount()
    {
        Integer id;
        String username;
        String email;
        String fullName;
        System.out.println("Nhập ID cần sửa: ");
        while (true) {
            id = ScannerUtils.inputIntGreaterThenZero();

            if (!accountController.checkExistID(id)) {
                System.out.println(" ID này không ton tai. Nhap lai: ");
            } else {
                break;
            }
        }

        System.out.println("Nhập username: ");
        while (true) {
            username = ScannerUtils.inputString();
            // check trung
            if (accountController.checkUsernameExist(username, id)) {
                System.out.println("Username đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        System.out.println("Nhập email: ");
        while (true) {
            email = ScannerUtils.inputEmail();
            // check trung
            if (accountController.checkEmailExist(email)) {
                System.out.println("email đã tồn tại. Nhập lại:");
                continue;
            }
            break;
        }

        System.out.println("Nhập fullName: ");
        fullName = ScannerUtils.inputString();

        System.out.println("Chọn ID department: ");
        List<Department> departments = departmentController.findAll();
        Integer depID;
        while (true) {
            for (Department department : departments) {
                System.out.println("ID: " + department.getId() + ", DepartmentName: " + department.getName());
            }
            depID = ScannerUtils.inputIntGreaterThenZero();
            // check departmentID có tồn tại ko
            boolean checkExists = departmentController.checkExistID(depID);//Integer.valueOf("abc")
            if (!checkExists) {
                System.out.println("Không ton tại deparmentID này:");

            } else {
                break;
            }
        }

        System.out.println("Chọn ID position: ");
        List<Position> positions = positionController.findAll();
        Integer poID;
        while (true) {
            for (Position position : positions) {
                System.out.println("ID: " + position.getId() + ", PositionName: " + position.getName());
            }

            poID = ScannerUtils.inputIntGreaterThenZero();
            // check positionID có tồn tại ko
            boolean checkExists = checkExistPosition(positions, String.valueOf(poID));
            if (!checkExists) {
                System.out.println("Không ton tại positionID này:");
            } else {
                break;
            }
        }

        boolean check = accountController.update(id, username, fullName, email, depID, poID);
        if (check) {
            System.out.println("Update thành công");
        } else {
            System.out.println("Update thất bại");
        }
    }

    public boolean checkExistPosition(List<Position> positions, String id) {
        for (Position position : positions) {
            if (id.equals(String.valueOf(position.getId()))) {
                return true;
            }
        }
        return false;
    }

}

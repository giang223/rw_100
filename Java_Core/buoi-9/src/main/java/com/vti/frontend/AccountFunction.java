package com.vti.frontend;

import com.vti.backend.controller.AccountController;
import com.vti.backend.controller.DepartmentController;
import com.vti.backend.controller.PositionController;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

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
            System.out.println("| 5. Thoát                                    |");
            System.out.println("===============================================");
            System.out.print("Mời bạn chọn chức năng (1-5): ");
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
        Account account = inputAccountData(null);

        boolean check = accountController.create(account.getEmail(), account.getUsername(), account.getFullName(), account.getDepartment().getId(), account.getPosition().getId());
        if (check) {
            System.out.println("Thêm mới thành công");
        }
    }

    public void deleteAccount()
    {
        System.out.println("Nhập ID của tài khoản cần xóa: ");
        int id;

        while(true)
        {
            id = scanner.nextInt();
            scanner.nextLine();

            if (id <= 0 ) {
                System.out.println("Nhập lai ID: ");
                continue;
            }

            if(!accountController.checkExistID(id))
            {
                System.out.println("ID nay ko ton tai, nhap lai: ");
                continue;
            }
            break;
        }

        boolean check = accountController.delete(id);
        if (check) {
            System.out.println("Xóa thành công");
        }
    }

    public void updateAccount()
    {
        System.out.println("Nhập ID account cần sửa: ");
        int id;
        while(true) {
            id = scanner.nextInt();
            scanner.nextLine();
            if(!accountController.checkExistID(id))
            {
                System.out.println("ID nay ko ton tai, nhap lai: ");
                continue;
            }
            break;
        }
        Account account = inputAccountData(id);
        boolean check = accountController.update(id, account.getUsername(), account.getFullName(), account.getEmail(), account.getDepartment().getId(), account.getPosition().getId());
        if (check) {
            System.out.println("Update thành công");
        }
    }

    private Account inputAccountData(Integer id)
    {
        String username = "";
        String fullName = "";
        String email = "";
        int departmentId = 0;
        int positionId = 0;

        while (true) {
            System.out.print("Nhập username: ");
            username = scanner.nextLine().trim();

            if(Objects.isNull(username) || username.isEmpty())
            {
                System.out.println("Nhập lại username: ");
                continue;
            }
            else
            {
                if(accountController.checkExistUsernameOrEmailAndIdNot(username, "", id))
                {
                    System.out.println("Username này đã được sử dụng, Nhập lại: ");
                    continue;
                }
                break;
            }
        }

        while (true) {
            System.out.print("Nhập fullName: ");
            fullName = scanner.nextLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("Fullname không được để trống! Nhập lại.");
                continue;
            }
            break;
        }

        while(true)
        {
            System.out.print("Nhập email: ");
            email = scanner.nextLine().trim();
            if(Objects.isNull(email) || email.isEmpty())
            {
                System.out.println("Nhập lại email: ");
                continue;
            }
            int indexOfAt = email.indexOf("@");
            if (indexOfAt <= 0 || indexOfAt == email.length() - 1) {
                System.out.println("Email không hợp lệ ! Nhập lại.");
                continue;
            }
            if (accountController.checkExistUsernameOrEmailAndIdNot("", email, id)) {
                System.out.println("Email này đã được sử dụng! Nhập lại.");
                continue;
            }
            break;
        }

        while(true)
        {
            System.out.println("Nhập ID phòng ban: ");
            departmentId = scanner.nextInt();
            scanner.nextLine();

            if (!departmentController.checkExistID(departmentId)) {
                System.out.println("ID phòng ban không tồn tại trên hệ thống! Nhập lại.");
                continue;
            }
            break;
        }

        while(true)
        {
            System.out.println("Nhập ID chức vụ: ");
            positionId = scanner.nextInt();
            scanner.nextLine();

            if (!positionController.checkExistID(positionId)) {
                System.out.println("ID chức vụ không tồn tại trên hệ thống! Nhập lại.");
                continue;
            }
            break;
        }

        Account account = new Account(0 ,username, fullName, email, new Department(departmentId, null), new Position(positionId, null), LocalDate.now());
        return account;
    }
}

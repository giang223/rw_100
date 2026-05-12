package com.vti.frontend;

import com.vti.backend.QLAccount;
import com.vti.entity.Account;

import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private static Scanner scanner = new Scanner(System.in);

    public static void run()
    {
        while(true)
        {
            System.out.println("\n========= QUẢN LÝ TÀI KHOẢN (ACCOUNT) =========");
            System.out.println("| 1. Xem danh sách tài khoản                  |");
            System.out.println("| 2. Thêm mới tài khoản                       |");
            System.out.println("| 3. Cập nhật Phòng ban/Chức vụ (theo ID)     |");
            System.out.println("| 4. Xóa tài khoản (theo Username)            |");
            System.out.println("| 5. Tìm kiếm chức vụ (theo tên)              |");
            System.out.println("| 6. Tìm kiếm chức vụ (theo tên và username)  |");
            System.out.println("| 7. Thoát                                    |");
            System.out.println("===============================================");
            System.out.print("Mời bạn chọn chức năng (1-7): ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Account> accounts = QLAccount.findAllAccount();
                    showAccounts(accounts);
                    break;
                case "2":
                    insertAccount();
                    break;
                case "3":
                    updateAccount();
                    break;
                case "4":
                    deleteAccountByUsername();
                    break;
                case "5":
                    showAccountsByFullName();
                    break;
                case "6":
                    showAccountsByFullNameAndUsername();
                    break;
                case "7":
                    System.out.println("Thoát!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    public static void showAccounts(List<Account> accounts) {
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

    public static void showAccountsByFullName()
    {
        System.out.print("Nhập đầy đủ tên cần tìm: ");
        String fullName = scanner.nextLine();
        List<Account> accounts = QLAccount.findAccountByFullName(fullName);
        showAccounts(accounts);
    }

    public static void showAccountsByFullNameAndUsername()
    {
        System.out.print("Nhập đầy đủ tên cần tìm: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập username cần tìm: ");
        String username = scanner.nextLine();
        List<Account> accounts = QLAccount.findAccountByFullNameANdUsername(fullName,username);
        showAccounts(accounts);
    }

    public static void insertAccount()
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

        if (QLAccount.createAccount(email, username, fullName, deptID, posID)) {
            System.out.println("Thêm tài khoản thành công!");
        } else {
            System.out.println("Thêm thất bại! Vui lòng kiểm tra lại dữ liệu.");
        }
    }

    public static void deleteAccountByUsername()
    {
        System.out.println("Nhập Username của tài khoản cần xóa: ");
        String username = scanner.nextLine();

        boolean check = QLAccount.deleteAccountByUsername(username);
        if (check) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa thất bại!");
        }
    }

    public static void updateAccount()
    {
        System.out.println("Nhập ID của tài khoản cần cập nhật: ");
        int accID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Department ID: ");
        int deptID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Position ID: ");
        int posID = scanner.nextInt();
        scanner.nextLine();

        boolean check = QLAccount.updateAccount(accID, deptID, posID);
        if(check)
        {
            System.out.println("Cập nhật nhân sự thành công!");
        }
        else
        {
            System.out.println("Cập nhật nhân sự thất bại!");
        }
    }
}

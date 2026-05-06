package com.vti.frontend;

import com.vti.backend.Management;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Management management = new Management(scanner);
        while (true) {
            System.out.println("\n========== QUẢN LÝ NHÂN SỰ==========");
            System.out.println("| 1. Thêm phòng ban                     |");
            System.out.println("| 2. Thêm chức vụ                       |");
            System.out.println("| 3. Thêm tài khoản                     |");
            System.out.println("| 4. Xem danh sách phòng ban            |");
            System.out.println("| 5. Xem danh sách chức vụ              |");
            System.out.println("| 6. Xem danh sách tài khoản            |");
            System.out.println("| 7. Tìm tài khoản theo tên phòng ban   |");
            System.out.println("| 8. Tìm tài khoản theo tên chức vụ     |");
            System.out.println("| 9. Xóa tài khoản theo Fullname        |");
            System.out.println("| 0. Thoát chương trình                 |");
            System.out.println("=========================================");
            System.out.println("Mời chọn chức năng");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    management.addDepartment();
                    break;
                case "2":
                    management.addPosition();
                    break;
                case "3":
                    management.addAccount();
                    break;
                case "4":
                    management.showDepartments();
                    break;
                case "5":
                    management.showPositions();
                    break;
                case "6":
                    management.showAccounts();
                    break;
                case "7":
                    management.searchAccountByDepartmentName();
                    break;
                case "8":
                    management.searchAccountByPositionName();
                    break;
                case "9":
                    management.deleteAccountByFullName();
                    break;
                case "0":
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại từ 0 đến 9.");
                    break;
            }
        }
    }
}

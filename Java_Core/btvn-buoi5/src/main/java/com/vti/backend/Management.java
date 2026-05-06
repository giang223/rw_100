package com.vti.backend;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    private Scanner scanner;
    private List<Department> departments = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Position> positions = new ArrayList<>();

    public Management(Scanner scanner)
    {
        this.scanner = scanner;
    }

    public void addDepartment() {
        System.out.println("Nhập ID phòng ban: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập tên phòng ban: ");
        String name = scanner.nextLine();

        departments.add(new Department(id, name));
        System.out.println("Thêm phòng ban thành công");
    }

    public void addPosition() {
        System.out.println("Nhập ID chức vụ: ");
        int id = scanner.nextInt();

        System.out.println("Chọn tên chức vụ: 1.DEV, 2.TEST, 3.SCRUM_MASTER, 4.PM");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Position.PositionName posName = null;
        while (posName == null) {
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    posName = Position.PositionName.DEV;
                    break;
                case "2":
                    posName = Position.PositionName.TEST;
                    break;
                case "3":
                    posName = Position.PositionName.SCRUM_MASTER;
                    break;
                case "4":
                    posName = Position.PositionName.PM;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, chọn lại");
                    break;
            }
        }
        positions.add(new Position(id, posName));
    }

    public void addAccount() {
        System.out.println("Nhập ID Account: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nhập email: ");
        String email = scanner.nextLine();

        System.out.println("Nhập username: ");
        String username = scanner.nextLine();

        System.out.println("Nhập FullName: ");
        String fullName = scanner.nextLine();

        Department findDept = null;
        while (findDept == null) {
            if (departments.isEmpty()) {
                System.out.println("Chưa có phòng ban nào! Vui lòng thoát ra thêm phòng ban trước.");
                return;
            }
            System.out.println("\n Danh sách phòng ban hiện có ");
            for (Department dept : departments) {
                System.out.println(dept);
            }

            System.out.print("Nhập ID phòng ban muốn chọn: ");
            int deptId = scanner.nextInt();
            scanner.nextLine();

            for (Department dept : departments) {
                if (dept.getId() == deptId) {
                    findDept = dept;
                    break;
                }
            }

            if (findDept == null) {
                System.out.println("ID không đúng, vui lòng chọn lại");
            }
        }

        Position findPos = null;
        while (findPos == null) {
            if (positions.isEmpty()) {
                System.out.println("Chưa có chức vụ nào! Vui lòng thoát ra thêm chức vụ trước.");
                return;
            }
            System.out.println("\n Danh sách chức vụ hiện có ");

            for (Position pos : positions) {
                System.out.println(pos);
            }

            System.out.print("Nhập ID chức vụ muốn chọn: ");
            int posID = scanner.nextInt();
            scanner.nextLine();

            for (Position pos : positions) {
                if (pos.getId() == posID) {
                    findPos = pos;
                    break;
                }
            }
            if (findPos == null) {
                System.out.println("ID không đúng, chọn lại");
            }
        }

        Account account = new Account(id, username, email, fullName, findDept, findPos);
        accounts.add(account);
    }

    public void showDepartments() {
        if (departments.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("\n DANH SÁCH PHÒNG BAN ");
        for (Department d : departments) {
            System.out.println(d);
        }
    }

    public void showPositions() {
        if (positions.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("\n DANH SÁCH CHỨC VỤ ");
        for (Position p : positions) {
            System.out.println(p);
        }
    }

    public void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("\n DANH SÁCH TÀI KHOẢN ");

        for (Account acc : accounts)
        {
            System.out.println(acc);
        }
    }

    public void searchAccountByDepartmentName()
    {
        showDepartments();

        System.out.print("Nhập tên phòng ban muốn tìm: ");
        String depName = scanner.nextLine();
        boolean isFound = false;
        System.out.println("\nKết quả tìm kiếm theo phòng ban: " + depName);

        for (Account acc : accounts)
        {
            if (acc.getDepartment().getName().equalsIgnoreCase(depName)) {
                System.out.println(acc);
                isFound = true;
            }
        }

        if (!isFound)
        {
            System.out.println("Không có tài khoản nào thuộc phòng ban này.");
        }
    }

    public void searchAccountByPositionName()
    {
        showPositions();

        System.out.println("Nhập tên chức vụ muốn tìm (DEV, TEST, SCRUM_MASTER, PM): ");
        String posName = scanner.nextLine();
        boolean isFound = false;
        System.out.println("\nKết quả tìm kiếm theo phòng ban: " + posName );

        for (Account acc : accounts)
        {
            if (acc.getPosition().getName().toString().equalsIgnoreCase(posName)) {
                System.out.println(acc);
                isFound = true;
            }
        }

        if (!isFound)
        {
            System.out.println("Không có tài khoản nào thuộc phòng ban này.");
        }
    }

    public void deleteAccountByFullName()
    {
        System.out.print("Nhập Fullname của tài khoản muốn xóa: ");
        String deleteName = scanner.nextLine();
        int count = 0;

        for (int i = accounts.size() - 1; i >= 0; i--)
        {
            if(accounts.get(i).getFullName().equalsIgnoreCase(deleteName))
            {
                accounts.remove(i);
                count++;
            }
        }

        if(count > 0)
        {
            System.out.println("Đã xóa thành công " + count + " tài khoản có tên: " + deleteName);
        }
        else
        {
            System.out.println("Không tìm thấy tài khoản nào có tên " + deleteName + " để xóa.");
        }
    }
}

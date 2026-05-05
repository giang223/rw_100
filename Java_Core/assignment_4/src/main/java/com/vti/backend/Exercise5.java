package com.vti.backend;

import com.vti.entity.Employee;
import com.vti.entity.Engineer;
import com.vti.entity.Staff;
import com.vti.entity.Worker;

import java.util.List;
import java.util.Scanner;

public class Exercise5 {
    public static void Question1()
    {
        Staff staff1 = new Worker("Nguyễn Văn A", 20, Staff.Gender.MALE, "Hà Nội", 1);
        Staff staff2 = new Engineer("Trần Thị B ", 20, Staff.Gender.MALE, "TP HCM", "A");
        Staff staff3 = new Employee("Đào Văn C", 20, Staff.Gender.MALE, "Đà Nẵng", "B");

        System.out.println(staff1);
        System.out.println(staff2);
        System.out.println(staff3);
    }

    private static Scanner scanner = new Scanner(System.in);
    public static void Question2(List<Staff> staffList)
    {
        while(true) {
            System.out.println("\n===== STAFF MANAGEMENT SYSTEM =====");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Tìm kiếm theo tên");
            System.out.println("3. Thông tin về danh sách các cán bộ");
            System.out.println("4. Delete cán bộ theo tên");
            System.out.println("5. Thoát chương trình");

            String choice = scanner.nextLine();
            switch (choice)
            {
                case "1":
                    System.out.println("Chọn kiểu: 1.Worker | 2.Engineer | 3.Employee");
                    String type = scanner.nextLine();

                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();

                    System.out.print("Nhập tuổi: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nhập giới tính (1.Male, 2.Female, 3.Other): ");
                    String gChoice = scanner.nextLine();
                    Staff.Gender gender = Staff.Gender.OTHER;
                    if (gChoice.equals("1")) gender = Staff.Gender.MALE;
                    if (gChoice.equals("2")) gender = Staff.Gender.FEMALE;

                    System.out.print("Nhập địa chỉ: ");
                    String address = scanner.nextLine();

                    if (type.equals("1")) {
                        System.out.print("Bậc (1-10): ");
                        int level = scanner.nextInt();
                        scanner.nextLine();
                        staffList.add(new Worker(name, age, gender, address, level));
                    } else if (type.equals("2")) {
                        System.out.print("Ngành đào tạo: ");
                        String major = scanner.nextLine();
                        staffList.add(new Engineer(name, age, gender, address, major));
                    } else {
                        System.out.print("Công việc: ");
                        String task = scanner.nextLine();
                        staffList.add(new Employee(name, age, gender, address, task));
                    }
                    System.out.println("Thêm cán bộ!");
                    break;

                case "2":
                    System.out.print("Nhập tên cần tìm kiếm: ");
                    String searchName = scanner.nextLine();
                    for (Staff staff : staffList)
                    {
                        if(staff.getFullName().equalsIgnoreCase(searchName))
                        {
                            System.out.println(staff);
                        }
                    }
                    break;

                case "3":
                    if(staffList.isEmpty())
                    {
                        System.out.println("Danh sách nhân viên hiện đang trống!");
                    }
                    else
                    {
                        for (Staff staff : staffList)
                        {
                            System.out.println(staff);
                        }
                    }
                    break;

                case "4":
                    System.out.println("Nhập tên cán bộ cần xóa: ");
                    String deleteName = scanner.nextLine();
                    for (int i = staffList.size() - 1; i >= 0; i--)
                    {
                        Staff s = staffList.get(i);
                        if (s.getFullName().equalsIgnoreCase(deleteName))
                        {
                            staffList.remove(i);
                        }
                    }
                    break;

                case "5":
                    System.out.println("Thoát chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }
}

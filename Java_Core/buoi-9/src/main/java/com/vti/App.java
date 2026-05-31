package com.vti;

import com.vti.frontend.AccountFunction;
import com.vti.frontend.DepartmentFunction;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        while(true) {
            System.out.println("========= QUẢN LÝ CHỨC NĂNG =========");
            System.out.println("| 1. Chức năng tài khoản            |");
            System.out.println("| 2. Chức năng Phòng ban            |");
            System.out.println("| 3.Thoát                           |");
            String choice = scanner.nextLine();
            switch (choice)
            {
                case "2":
                    DepartmentFunction departmentFunction = new DepartmentFunction();
                    departmentFunction.run();
                    break;
                case "1":
                    AccountFunction accountFunction = new AccountFunction();
                    accountFunction.run();
                    break;
                case "3":
                    return;
            }

        }
    }
}

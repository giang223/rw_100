package Exercise;

import Table.Account;

import java.util.List;

public class Exercise6 {
    // 1. Tạo method để in ra các số chẵn nguyên dương nhỏ hơn 10
    public static void question1() {
        System.out.println("Question 1: Số chẵn nguyên dương < 10");
        for (int i = 2; i < 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 2. In thông tin các account
    public static void question2(List<Account> accounts)
    {
        System.out.println("\nQuestion 2: Thông tin Accounts");
        for (Account acc : accounts) {
            System.out.println("ID: " + acc.id + ", username: " + acc.username + ", fullName: " + acc.fullName
                    + ", Position: " + acc.position.name);
        }
    }

    // 3. In ra các số nguyên dương nhỏ hơn 10
    public static void question3() {
        System.out.println("\nQuestion 3: Số nguyên dương < 10");
        for (int i = 1; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

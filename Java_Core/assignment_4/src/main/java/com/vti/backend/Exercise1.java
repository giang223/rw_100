package com.vti.backend;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Group;
import com.vti.entity.Position;

import java.time.LocalDate;
import java.util.List;

public class Exercise1 {
    public static void Question1()
    {
        Department department1 = new Department();
        Department department2 = new Department("A");
    }

    public static void Question2(Position position, LocalDate createDate)
    {
        Account account1 = new Account();
        Account account2 = new Account(1, "vana@gmail.com", "vana", "Nguyễn Văn", "A");
        Account account3 = new Account(1, "vana@gmail.com", "vana", "Nguyễn Văn", "A", position);
        Account account4 = new Account(1, "vana@gmail.com", "vana", "Nguyễn Văn", "A", position, createDate);
    }

    public static void Question3(Account creator, List<Account> accountList, String[] usernames, LocalDate createDate)
    {
        Group group1 = new Group();
        Group group2 = new Group("A", creator, accountList, createDate);
        Group group3 = new Group("B", creator, usernames, createDate);
    }

}

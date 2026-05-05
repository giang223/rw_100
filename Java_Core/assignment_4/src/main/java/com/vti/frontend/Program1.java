package com.vti.frontend;

import com.vti.backend.Exercise1;
import com.vti.entity.Account;
import com.vti.entity.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Program1 {
    public static void main(String[] args) {
        Exercise1.Question1();

        Position position = new Position();
        position.setName(Position.PositionName.DEV);
        Exercise1.Question2(position, LocalDate.now());

        Account creator = new Account(1, "admin@gmail.com", "admin", "Le", "Van Admin");
        List<Account> accountList = new ArrayList<>();
        Account acc1 = new Account(1, "vana@gmail.com", "vana", "Nguyễn Văn", "A");
        Account acc2 = new Account(2, "thib@gmail.com", "thib", "Trần Thị", "B", position);
        Account acc3 = new Account(3, "hongc@gmail.com", "hongc", "Phạm Hồng", "C", position, LocalDate.now());
        accountList.add(acc1);
        accountList.add(acc2);
        accountList.add(acc3);
        String[] usernames = {"user1", "user2", "user3"};
        Exercise1.Question3(creator,accountList,usernames, LocalDate.now());
    }
}

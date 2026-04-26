package Exercise;

import Table.Account;

import java.time.LocalDate;

public class Exercise2 {
    public static void question1(){
        Account[] accounts = new Account[5];

        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new Account();

            accounts[i].email = "Email " + (i + 1);
            accounts[i].username = "User name " + (i + 1);
            accounts[i].fullName = "Full name " + (i + 1);
            accounts[i].createDate = LocalDate.now();
        }
    }
}

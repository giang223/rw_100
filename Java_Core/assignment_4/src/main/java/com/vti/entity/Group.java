package com.vti.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;
    private Account creator;
    private List<Account> accounts;
    private LocalDate createDate;

    // không có parameters
    public Group()
    {

    }

    // Có các parameter là GroupName, Creator, array Account[] accounts, CreateDate
    public Group(String groupName, Account creator, List<Account> accounts, LocalDate createDate)
    {
        this.name = groupName;
        this.creator = creator;
        this.accounts = accounts;
        this.createDate = createDate;
    }

    public Group(String groupName, Account creator, String[] usernames, LocalDate createDate)
    {
        this.name = groupName;
        this.creator = creator;
        this.createDate = createDate;
        this.accounts = new ArrayList<>();
        for(int i = 0; i < usernames.length; i++)
        {
            Account acc = new Account(i, null, usernames[i],null, null);
            this.accounts.add(acc);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

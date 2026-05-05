package com.vti.entity;

public class Account1 {
    private String id;
    private String name;
    private int balance;

    public Account1(String id, String name, int balance)
    {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int credit(int amount) {
        this.balance += amount;
        return balance;
    }

    public int debit(int amount) {
        if (amount <= balance) {
            this.balance -= amount;
        } else {
            System.out.println("Số dư không đủ!");
        }
        return balance;
    }

    public int transferTo(Account1 account, int amount) {
        if (amount <= balance) {
            this.debit(amount);
            account.credit(amount);
        } else {
            System.out.println("Chuyển tiền thất bại!");
        }
        return balance;
    }
}

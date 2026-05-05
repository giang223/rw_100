package com.vti.entity;

public class Worker extends Staff{
    private int level;

    public Worker(String fullName, int age, Gender gender, String address, int level) {
        super(fullName, age, gender, address);
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Worker [Level=" + level + "]";
    }
}

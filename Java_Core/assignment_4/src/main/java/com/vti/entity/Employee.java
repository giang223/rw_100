package com.vti.entity;

public class Employee extends Staff{
    private String task;

    public Employee(String fullName, int age, Gender gender, String address, String task) {
        super(fullName, age, gender, address);
        this.task = task;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Employee [Task=" + task + "]";
    }
}

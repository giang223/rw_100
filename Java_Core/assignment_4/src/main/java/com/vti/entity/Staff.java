package com.vti.entity;

public class Staff {
    private String fullName;
    private int age;
    private Gender gender;
    private String address;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public Staff(String fullName, int age, Gender gender, String address) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }
}

package com.vti.entity;

import java.time.LocalDate;

public class Account {
    private int id;
    private String username;
    private String email;
    private String fullName;
    private Department department;
    private Position position;
    private LocalDate createDate;

    public Account()
    {

    }

    // Có các parameter là id, Email, Username, FirstName, LastName (với FullName = FirstName + LastName)
    public Account(int id, String email, String userName, String firstName, String lastName)
    {
        this.id = id;
        this.email = email;
        this.username = userName;
        this.fullName = firstName + " " + lastName;
    }

    public Account(int id, String username, String email, String fullName, Department department, Position position) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = LocalDate.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", createDate=" + createDate +
                '}';
    }
}

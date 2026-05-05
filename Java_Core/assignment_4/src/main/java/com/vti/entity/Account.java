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

    // Không có parameters
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

    // Có các parameter là id, Email, Username, FirstName, LastName (với FullName = FirstName + LastName) và
    // Position của User, default createDate = now
    public Account(int id, String email, String username, String firstName, String lastName, Position position)
    {
        this(id, email, username, firstName, lastName);
        this.position = position;
        this.createDate = LocalDate.now();
    }

    // Có các parameter là id, Email, Username, FirstName, LastName (với FullName = FirstName + LastName)
    // và Position của User, createDate
    public Account(int id, String email, String username, String firstName, String lastName, Position position, LocalDate createDate)
    {
        this(id, email, username, firstName, lastName);
        this.position = position;
        this.createDate = createDate;
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
}

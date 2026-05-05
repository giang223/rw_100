package com.vti.entity;

public class Department {
    private int id;
    private String name;


    public Department() {}

    public Department(String nameDepartment)
    {
        id = 0;
        name = nameDepartment;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
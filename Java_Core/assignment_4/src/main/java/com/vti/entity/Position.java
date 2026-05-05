package com.vti.entity;

public class Position {
    private int id;
    private PositionName name;

    public PositionName getName() {
        return name;
    }

    public void setName(PositionName name) {
        this.name = name;
    }

    public enum PositionName {
        DEV, TEST, SCRUM_MASTER, PM
    }
}

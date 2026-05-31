package com.vti.dto.csv;

public class AccountCsv {
    private String username;
    private String fullName;
    private String email;
    private String departmentId;
    private String positionId;

    public AccountCsv( String username, String fullName, String email, String departmentId, String positionId) {

        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.departmentId = departmentId;
        this.positionId = positionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return username + "," + fullName + "," + email + "," + departmentId + "," + positionId;
    }
}

package com.vti.backend;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLAccount {
    public static void question1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM `account` a " +
                    "LEFT JOIN department d on a.department_id = d.department_id " +
                    "LEFT JOIN position p on a.position_id = p.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            List<Account> accounts = new ArrayList<>();
            while(rs.next())
            {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                int positionId = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);

                Account account = new Account(username, fullName, email, new Department(departmentId, departmentName), new Position(positionId, positionName));

                accounts.add(account);
            }

            for(Account account : accounts)
            {
                System.out.println(account);
            }
        } catch (Exception e) {
            System.out.println("Kết nối DB không thành công");
        }
    }

    public static void question2(String inputName) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM account a " +
                    "JOIN department d ON a.department_id = d.department_id " +
                    "JOIN position p ON a.position_id = p.position_id " +
                    "WHERE a.full_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputName);
            ResultSet rs = preparedStatement.executeQuery();

            List<Account> accounts = new ArrayList<>();
            while(rs.next())
            {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                int positionId = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);

                Account account = new Account(username, fullName, email, new Department(departmentId, departmentName), new Position(positionId, positionName));

                accounts.add(account);
            }
            for (Account account : accounts)
            {
                System.out.println(account);
            }
        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công");
        }
    }

    public static void question3(String inputFullName, String inputUsername)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM account a " +
                    "JOIN department d ON a.department_id = d.department_id " +
                    "JOIN position p ON a.position_id = p.position_id " +
                    "WHERE a.full_name = ?" +
                    "AND a.username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputFullName);
            preparedStatement.setString(2, inputUsername);
            ResultSet rs = preparedStatement.executeQuery();

            List<Account> accounts = new ArrayList<>();
            while(rs.next())
            {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                int positionId = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);

                Account account = new Account(username, fullName, email, new Department(departmentId, departmentName), new Position(positionId, positionName));

                accounts.add(account);
            }
            for (Account account : accounts)
            {
                System.out.println(account);
            }
        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công");
        }
    }
}

package com.vti.backend;

import com.vti.utils.JDBCUtils;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLAccount {
    public static List<Account> findAllAccount() {
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM `account` a " +
                    "LEFT JOIN department d on a.department_id = d.department_id " +
                    "LEFT JOIN position p on a.position_id = p.position_id;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                int id = rs.getInt("account_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                int positionId = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);
                LocalDate createDate = rs.getDate("create_date").toLocalDate();

                Account account = new Account(id, username, fullName, email, new Department(departmentId, departmentName), new Position(positionId, positionName), createDate);

                accounts.add(account);
            }
            JDBCUtils.closeConnection(connection, statement, rs);
        } catch (Exception e) {
            System.out.println("Kết nối DB không thành công");
        }

        return accounts;
    }

    public static List<Account> findAccountByFullName(String inputName) {
        List<Account> accounts = new ArrayList<>();
        try
        {
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM account a " +
                    "JOIN department d ON a.department_id = d.department_id " +
                    "JOIN position p ON a.position_id = p.position_id " +
                    "WHERE a.full_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputName);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("account_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                int positionId = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);
                LocalDate createDate = rs.getDate("create_date").toLocalDate();

                Account account = new Account(id, username, fullName, email, new Department(departmentId, departmentName), new Position(positionId, positionName), createDate);

                accounts.add(account);
            }
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }

        return accounts;
    }

    public static List<Account> findAccountByFullNameANdUsername(String inputFullName, String inputUsername)
    {
        List<Account> accounts = new ArrayList<>();
        try
        {
            Connection connection = JDBCUtils.getConnection();

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

            while(rs.next())
            {
                int id = rs.getInt("account_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String fullName = rs.getString("full_name");
                int departmentId = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                int positionId = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);
                LocalDate createDate = rs.getDate("create_date").toLocalDate();

                Account account = new Account(id, username, fullName, email, new Department(departmentId, departmentName), new Position(positionId, positionName), createDate);

                accounts.add(account);
            }
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công");
        }
        return accounts;
    }

    public static boolean createAccount(String email, String username, String fullName, int depId, int posId)
    {
        try
        {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO account (email, username, full_name, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, depId);
            preparedStatement.setInt(5, posId);

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection, preparedStatement, null);

            return c > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteAccountByUsername(String username) {
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE username = ?");
            preparedStatement.setString(1, username);

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAccount(int accountId, int newDepId, int newPosId) {
        try {
            Connection connection = JDBCUtils.getConnection();
            // Câu lệnh SQL update các thông tin dựa trên username
            String sql = "UPDATE account SET department_id = ?, position_id = ? WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, newDepId);
            preparedStatement.setInt(2, newPosId);
            preparedStatement.setInt(3, accountId);

            int count = preparedStatement.executeUpdate();

            JDBCUtils.closeConnection(connection, preparedStatement, null);
            return count > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật tài khoản!");
            e.printStackTrace();
            return false;
        }
    }
}

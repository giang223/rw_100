package com.vti.backend.repository.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.JDBCUtils;
import com.vti.enums.PositionName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements IAccountRepository {
    @Override
    public List<Account> findAll() {
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
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public boolean create(String email, String username, String fullName, int depId, int posId) {
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

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE account_id = ?");
            preparedStatement.setInt(1, id);

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId)
    {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE ACCOUNT SET full_name = ?, email = ?, username = ?, department_id = ?, position_id = ? WHERE account_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updateName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);
            preparedStatement.setInt(6, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        return false;
    }
}

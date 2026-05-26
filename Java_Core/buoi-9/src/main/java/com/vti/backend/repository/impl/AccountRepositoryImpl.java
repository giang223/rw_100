package com.vti.backend.repository.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.utils.JDBCUtils;
import com.vti.enums.PositionName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class AccountRepositoryImpl implements IAccountRepository {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM `account` a " +
                    "LEFT JOIN department d on a.department_id = d.department_id " +
                    "LEFT JOIN position p on a.position_id = p.position_id;";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);

        }
        return accounts;
    }

    @Override
    public Map<String, Account> mapByUsername() {
        Map<String, Account> accounts = new HashMap<>();
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM `account` a " +
                    "LEFT JOIN department d on a.department_id = d.department_id " +
                    "LEFT JOIN position p on a.position_id = p.position_id;";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

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

                accounts.put(username, account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);

        }
        return accounts;
    }

    @Override
    public Map<String, Account> mapByEmail() {
        Map<String, Account> accounts = new HashMap<>();
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT a.*, d.department_name, p.position_name " +
                    "FROM `account` a " +
                    "LEFT JOIN department d on a.department_id = d.department_id " +
                    "LEFT JOIN position p on a.position_id = p.position_id;";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);

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

                accounts.put(email, account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, statement, rs);

        }
        return accounts;
    }

    @Override
    public boolean create( String username, String fullName, String email, int depId, int posId) {
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO account (email, username, full_name, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, depId);
            preparedStatement.setInt(5, posId);

            int c = preparedStatement.executeUpdate();

            return c > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM account WHERE account_id = ?");
            preparedStatement.setInt(1, id);

            int c = preparedStatement.executeUpdate();
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public boolean update(int id, String username, String fullName, String email, int departmentId, int positionId)
    {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "UPDATE ACCOUNT SET full_name = ?, email = ?, username = ?, department_id = ?, position_id = ? WHERE account_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            preparedStatement.setInt(4, departmentId);
            preparedStatement.setInt(5, positionId);
            preparedStatement.setInt(6, id);

            int c = preparedStatement.executeUpdate();// executeUpdate sẽ trả về 1 số nguyên, đại diện cho số dòng bị thay đổi trong DB
            return c > 0;
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public boolean checkExistID(int id) {
        boolean check = false;

        try
        {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT * FROM account WHERE account_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                check = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }

        return check;
    }

    @Override
    public boolean createListAccount(List<Account> list) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT IGNORE INTO account (email, username, full_name, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            for (Account account : list) {
                preparedStatement.setString(1, account.getEmail());
                preparedStatement.setString(2, account.getUsername());
                preparedStatement.setString(3, account.getFullName());
                preparedStatement.setInt(4, account.getDepartment().getId());
                preparedStatement.setInt(5, account.getPosition().getId());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public boolean checkUsernameExist(String username, Integer id) {
        boolean checkUsernameExist = false;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();

            String sql =  "select * from account where username like ? and (account_id != ? or ? is null);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, id);

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                checkUsernameExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        return checkUsernameExist;
    }

    @Override
    public boolean checkEmailExist(String email) {
        boolean checkEmailExist = false;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng account
            String sql = "select * from account where email like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                checkEmailExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        return checkEmailExist;
    }


}

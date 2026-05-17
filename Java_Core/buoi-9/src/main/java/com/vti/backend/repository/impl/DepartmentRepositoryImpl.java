package com.vti.backend.repository.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.entity.Department;
import com.vti.enums.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from department;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name
                Department dep = new Department(id, name);
                departments.add(dep);
            }
            JDBCUtils.closeConnection(connection, statement, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public boolean create(String name) {
        try {

            Connection connection = JDBCUtils.getConnection();

            String sql = "insert into department (department_name) values (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            int c = statement.executeUpdate();// trả ra số row thay đổi trong DB
            JDBCUtils.closeConnection(connection, statement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: xóa department
            String sql = "delete from department where department_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            // thực thi câu sql
            int c = statement.executeUpdate();// trả ra số row thay đổi trong DB
//            if (c>0) {
//                return true;
//            } else {
//                return false;
//            }
            JDBCUtils.closeConnection(connection, statement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, String name) {
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: update department
            String sql = "update department set department_name = ? where department_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            // thực thi câu sql
            int c = statement.executeUpdate();// trả ra số row thay đổi trong DB
            JDBCUtils.closeConnection(connection, statement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

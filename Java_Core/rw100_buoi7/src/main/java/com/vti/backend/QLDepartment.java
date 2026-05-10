package com.vti.backend;


import com.vti.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment {

    // lấy ds các phòng ban trong DB và in ra
    public static void showDepartment(){
        try {
            // b1: kết nối đến DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Kết nối DB thành công");
            }
           //  b2: lấy dữ liệu từ bảng department
            String sql = "select * from department;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name
                Department dep = new Department(id, name);
                departments.add(dep);
            }
            for (Department de: departments) {
                System.out.println(de);
            }

        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
        }
    }

    // tìm các phòng ban có chữ xyz  chưa biết trước
    //  select * from department where department_name like '...';

    public static void findByNameAndId(String searchName, int searchId){
        try {
            // b1: kết nối đến DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Kết nối DB thành công");
            }
            // b2: tìm các phòng ban có tên là name
            String sql = "select * from department where department_name like ? and department_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, searchName);// truyền giá trị searchName vào ? đầu tiên
            statement.setInt(2, searchId);// truyền giá trị 2 vào ? thứ2
            ResultSet rs = statement.executeQuery();
            List<Department> departments = new ArrayList<>();// lưu lại dữ liệu lấy từ DB
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name
                Department dep = new Department(id, name);
                departments.add(dep);
            }
            for (Department de: departments) {
                System.out.println(de);
            }

        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
        }
    }

    public static void question1()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();

            String sql = "SELECT d.*, COUNT(a.department_id)\n" +
                    "FROM department d JOIN `account` a ON d.department_id = a.department_id\n" +
                    "GROUP BY d.department_id\n" +
                    "HAVING COUNT(a.department_id) >= 2;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Department> departments = new ArrayList<>();
            while (rs.next()) {// lặp qua qua từng dòng của rs
                int id = rs.getInt("department_id");// lấy giá trị từ cloumn department_id
                String name = rs.getString("department_name");//lấy giá trị từ cloumn department_name
                Department dep = new Department(id, name);
                departments.add(dep);
            }
            for(Department de : departments)
            {
                System.out.println(de);
            }

        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công!");
        }
    }
}
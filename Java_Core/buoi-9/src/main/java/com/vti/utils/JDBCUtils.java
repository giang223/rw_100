package com.vti.utils;

import java.sql.*;

public class JDBCUtils {
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/rw100_testing_system";
        String username = "root";
        String password = "1234";// mk mysql

        try {
            // b1: kết nối đến DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
        }
        return null;
    }

    // close cho 3 interface connection,statement,rs
    public static void closeConnection
    (Connection connection, Statement statement, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
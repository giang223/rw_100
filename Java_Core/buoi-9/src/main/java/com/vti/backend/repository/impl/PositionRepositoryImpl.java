package com.vti.backend.repository.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.entity.Position;
import com.vti.enums.JDBCUtils;
import com.vti.enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PositionRepositoryImpl implements IPositionRepository {
    @Override
    public List<Position> findAll() {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM position;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Position> positions = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);

                Position po = new Position(id, positionName);
                positions.add(po);
            }
            JDBCUtils.closeConnection(connection, statement, rs);
            return positions;

        } catch (Exception e) {
            System.out.println("Kết nối DB ko thành công");
        }
        return null;
    }

    @Override
    public boolean create(String name) {
        try
        {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO position (position_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.toUpperCase());

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try
        {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM position WHERE position_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int c = preparedStatement.executeUpdate();
            JDBCUtils.closeConnection(connection, preparedStatement, null);
            // c= 0
            return c > 0;
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(int id, String name) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "update position set position_name = ? where position_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);

            int c = statement.executeUpdate();
            JDBCUtils.closeConnection(connection, statement, null);

            return c > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean checkExistNameAndIdNot(PositionName name, Integer id) {
        boolean check = false;

        try
        {
            Connection connection = JDBCUtils.getConnection();

            String sql = (Objects.nonNull(id))
                    ? "SELECT * FROM position WHERE position_name = ? AND position_id != ?"
                    : "SELECT * FROM position WHERE position_name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.name());
            if (Objects.nonNull(id)) {// check update
                preparedStatement.setInt(2, id);
            }

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {
                check = true;
            }

            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return check;
    }

    @Override
    public boolean checkExistID(int id) {
        boolean check = false;
        try {
            // b1: kết nối đến DB
            Connection connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from position where position_id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                check = true;
            }
            // đóng các kết nối
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }

        return check;
    }
}

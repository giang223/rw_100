package com.vti.backend.repository.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.entity.Position;
import com.vti.utils.JDBCUtils;
import com.vti.enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PositionRepositoryImpl implements IPositionRepository {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;
    @Override
    public List<Position> findAll() {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "SELECT * FROM position;";
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            List<Position> positions = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);

                Position po = new Position(id, positionName);
                positions.add(po);
            }
            return positions;

        } catch (SQLException e) {
            System.out.println("Kết nối DB ko thành công");
        }
        finally {
            JDBCUtils.closeConnection(connection, statement, rs);
        }
        return null;
    }

    @Override
    public boolean create(String name) {
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO position (position_name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.toUpperCase());

            int c = preparedStatement.executeUpdate();
            // c= 0
            return c > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try
        {
            connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM position WHERE position_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int c = preparedStatement.executeUpdate();
            // c= 0
            return c > 0;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }

        return false;
    }

    @Override
    public boolean update(int id, String name) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update position set position_name = ? where position_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int c = preparedStatement.executeUpdate();

            return c > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, null);
        }

        return false;
    }

    @Override
    public boolean checkExistNameAndIdNot(PositionName name, Integer id) {
        boolean check = false;

        try
        {
            connection = JDBCUtils.getConnection();

            String sql = (Objects.nonNull(id))
                    ? "SELECT * FROM position WHERE position_name = ? AND position_id != ?"
                    : "SELECT * FROM position WHERE position_name = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name.name());
            if (Objects.nonNull(id)) {// check update
                preparedStatement.setInt(2, id);
            }

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {
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
    public boolean checkExistID(int id) {
        boolean check = false;
        try {
            // b1: kết nối đến DB
            connection = JDBCUtils.getConnection();
            // b2: lấy dữ liệu từ bảng department
            String sql = "select * from position where position_id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();// thực thi câu lệnh sql và gán bảng trả ra vào ResultSet rs
            if (rs.next()) {// lặp qua qua từng dòng của rs
                check = true;
            }
        } catch (Exception e) {// show các lỗi lien quan đén logic xử lý
            e.printStackTrace();// show ra exception
        }
        finally {
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }

        return check;
    }
}

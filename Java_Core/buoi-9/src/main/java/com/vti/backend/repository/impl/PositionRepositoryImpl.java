package com.vti.backend.repository.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.entity.Position;
import com.vti.enums.JDBCUtils;
import com.vti.enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}

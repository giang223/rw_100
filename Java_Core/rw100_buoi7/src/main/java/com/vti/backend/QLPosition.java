package com.vti.backend;

import com.vti.utils.JDBCUtils;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QLPosition {
    public static List<Position> findAllPosition()
    {
        List<Position> positions = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT * FROM position;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next())
            {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);
                Position pos = new Position(id, positionName);
                positions.add(pos);
            }
            for (Position position : positions)
            {
                System.out.println(position);
            }
            JDBCUtils.closeConnection(connection, statement, rs);
        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công!");
        }
        return positions;
    }

    public static List<Position> findPositionByName(String inputName)
    {
        List<Position> positions = new ArrayList<>();
        try
        {
            Connection connection = JDBCUtils.getConnection();

            String sql = "SELECT * FROM position WHERE position_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputName.trim());
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);
                Position pos = new Position(id, positionName);
                positions.add(pos);
            }
            JDBCUtils.closeConnection(connection, preparedStatement, rs);
        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công!");
        }
        return positions;
    }

    public static boolean createPosition(String name)
    {
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

    public static boolean deletePositionByID(int id)
    {
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

    public static boolean updatePosition(int id, String updateName)
    {
        try {
            Connection connection = JDBCUtils.getConnection();
            // b2: update department
            String sql = "update position set position_name = ? where position_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, updateName);
            statement.setInt(2, id);

            int c = statement.executeUpdate();// trả ra số row thay đổi trong DB
            JDBCUtils.closeConnection(connection, statement, null);

            return c > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

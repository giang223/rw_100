package com.vti.backend;

import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {
    public static void question1()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();

            String sql = "SELECT * FROM position;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            List<Position> positions = new ArrayList<>();
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
        } catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công!");
        }
    }

    public static void question2(String inputName)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DBConnection.getConnection();

            String sql = "SELECT * FROM position WHERE position_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputName.trim());
            ResultSet rs = preparedStatement.executeQuery();

            List<Position> positions = new ArrayList<>();
            while(rs.next())
            {
                int id = rs.getInt("position_id");
                String name = rs.getString("position_name");
                PositionName positionName = PositionName.valueOf(name);
                Position pos = new Position(id, positionName);
                positions.add(pos);
            }
            for (Position p : positions) {
                System.out.println(p);
            }
        }
        catch (Exception e)
        {
            System.out.println("Kết nối DB không thành công!");
        }
    }
}

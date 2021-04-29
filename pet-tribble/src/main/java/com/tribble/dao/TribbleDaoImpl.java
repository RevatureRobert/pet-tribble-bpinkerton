package com.tribble.dao;

import com.tribble.model.Lab;
import com.tribble.model.Tribble;
import com.tribble.util.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TribbleDaoImpl implements Dao<Tribble>{
    @Override
    public boolean create(Tribble tribble) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try{
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO tribbles (" +
                    "tribble_name," +
                    "lab_id)" +
                    " VALUES (?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tribble.getName());
            stmt.setInt(2, tribble.getLab().getId());

            success = stmt.executeUpdate();

            if(success != 0)
                return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try{
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Tribble get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try{
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * from tribbles " +
                    "join labs on tribbles.lab_id = labs.lab_id " +
                    "WHERE tribble_id=?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Tribble(
                    rs.getInt("tribble_id"),
                    rs.getString("tribble_name"),
                    new Lab(
                            rs.getInt("lab_id"),
                            rs.getString("lab_name")
                    )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try{
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }


    public List<Tribble> getAll() {
        Connection connection = null;
        PreparedStatement stmt = null;
        List<Tribble> tribbles = new ArrayList<>();

        try{
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * from tribbles " +
                    "join labs on tribbles.lab_id = labs.lab_id";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                tribbles.add(new Tribble(
                        rs.getInt("tribble_id"),
                        rs.getString("tribble_name"),
                        new Lab(
                                rs.getInt("lab_id"),
                                rs.getString("lab_name")
                        )
                ));
            }
            return tribbles;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try{
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean update(Tribble tribble) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try{
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE tribbles SET " +
                    "tribble_name=?," +
                    "lab_id=?" +
                    "WHERE tribble_id = ?";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, tribble.getName());
            stmt.setInt(2,tribble.getLab().getId());
            stmt.setInt(3, tribble.getId());

            success = stmt.executeUpdate();

            if(success != 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try{
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try{
            connection = DAOUtilities.getConnection();
            String sql = "DELETE from tribbles where tribble_id=?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            success = stmt.executeUpdate();

            if(success != 0)
                return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try{
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}

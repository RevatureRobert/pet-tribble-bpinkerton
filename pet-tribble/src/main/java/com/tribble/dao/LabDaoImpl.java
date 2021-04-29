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

public class LabDaoImpl implements Dao<Lab>{
    @Override
    public boolean create(Lab lab) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try{
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO labs (" +
                    "lab_name)" +
                    " VALUES (?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, lab.getName());

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
    public Lab get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Lab lab = new Lab();


        try{
            connection = DAOUtilities.getConnection();
            String sql = "SELECT * from labs " +
                    "left join tribbles on tribbles.lab_id = labs.lab_id " +
                    "WHERE labs.lab_id=?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                lab.setId(id);
                lab.setName(rs.getString("lab_name"));
                if(rs.getString("tribble_id") != null)
                    lab.getTribbles().add(new Tribble(
                            rs.getInt("tribble_id"),
                            rs.getString("tribble_name")
                    ));
            }
            if(lab.getId() != 0)
                return lab;
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

    public List<Lab> getAll(){
        Connection connection = null;
        PreparedStatement stmt = null;
        List<Lab> labs = new ArrayList<>();
        List<Integer> labIds = new ArrayList<>();


        try{
            connection = DAOUtilities.getConnection();
            String sql = "SELECT lab_id from labs order by lab_id";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                labIds.add(rs.getInt("lab_id"));
            }
            for(Integer id : labIds){
                labs.add(
                  get(id)
                );
            }
            if(labs.size() > 0)
                return labs;
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
    public boolean update(Lab lab) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try{
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE labs SET " +
                    "lab_name=?" +
                    "WHERE lab_id = ?";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, lab.getName());
            stmt.setInt(2, lab.getId());

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
            String sql = "DELETE from labs where lab_id=?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            success = stmt.executeUpdate();
            sql = "DELETE from tribbles where lab_id=?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

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

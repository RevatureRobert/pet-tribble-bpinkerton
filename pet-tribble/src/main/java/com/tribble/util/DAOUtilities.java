package com.tribble.util;

import com.tribble.dao.LabDaoImpl;
import com.tribble.dao.TribbleDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtilities {
    private static final String CONNECTION_USERNAME = "postgres";
    private static final String CONNECTION_PASSWORD = "1324Pass!";
    private static final String URL = "jdbc:postgresql://database-1.chenkhwxqq2k.us-east-2.rds.amazonaws.com:5432/postgres";

    private static Connection connection;
    private static LabDaoImpl labDao;
    private static TribbleDaoImpl tribbleDao;

    public static TribbleDaoImpl getTribbleDao(){
        if(tribbleDao == null){
            tribbleDao = new TribbleDaoImpl();
        }
        return tribbleDao;
    }

    public static LabDaoImpl getLabDao(){
        if(labDao == null){
            labDao = new LabDaoImpl();
        }
        return labDao;
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }

        //If connection was closed then retrieve a new connection
        if (connection.isClosed()){
            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }
        return connection;
    }
}

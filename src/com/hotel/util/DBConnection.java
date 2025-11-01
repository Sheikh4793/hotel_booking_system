package com.hotel.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
    private static Connection connection;
    private static DBConnection instance;
    private static final String PROPERTIES_FILE = "src/com/hotel/DataBaseCredentials.properties";

    private DBConnection()throws IOException,ClassNotFoundException, SQLException {
        try(InputStream input = new FileInputStream(PROPERTIES_FILE)){
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            if(url == null || username == null || password == null){
                throw new IOException("Missing database properties in database credentials.");
            }

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,username,password);

        }

    }

    public static DBConnection getInstance()throws IOException,ClassNotFoundException, SQLException {
        if(instance == null || instance.connection == null|| instance.connection.isClosed()){
            try {
                instance = new DBConnection();
            }
            catch(SQLException | ClassNotFoundException | IOException e){
                throw new SQLException("Failed to create database connection.",e);
            }
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
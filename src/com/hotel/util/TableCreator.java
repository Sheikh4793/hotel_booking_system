package com.hotel.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {


    public static void createTable() throws Exception {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS hotel_locations(id SERIAL PRIMARY KEY, city_name VARCHAR(55) NOT NULL, state VARCHAR(55) NOT NULL, country VARCHAR(55) NOT NULL);""");
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS hotels(id  SERIAL PRIMARY KEY ,hotel_name VARCHAR(55) NOT NULL,location_id  INT REFERENCES hotel_locations(id),type VARCHAR(55) NOT NULL,contact_number VARCHAR(55) NOT NULL,email VARCHAR(55) NOT NULL,address VARCHAR(55) NOT NULL);
                    """);
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS hotel_rooms(id SERIAL PRIMARY KEY,hotel_id INT REFERENCES hotels(id),room_number VARCHAR(55) NOT NULL,type VARCHAR(55) NOT NULL,capacity INT NOT NULL,price_per_night DECIMAL(10,2) NOT NULL,is_available BOOLEAN DEFAULT TRUE);
                    """);
            stmt.executeUpdate("""
                    CREATE TABLE  IF NOT EXISTS hotel_customers(id SERIAL PRIMARY KEY,name VARCHAR(55) NOT NULL,email VARCHAR(55) UNIQUE NOT NULL,password TEXT NOT NULL,phone VARCHAR(25) NOT NULL,address VARCHAR(255) NOT NULL);""");
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS hotel_operators(id SERIAL PRIMARY KEY,hotel_id INT REFERENCES hotels(id),name VARCHAR(55) NOT NULL,email VARCHAR(55) UNIQUE NOT NULL,password  TEXT NOT NULL,role VARCHAR(55) NOT NULL);
                    """);
            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS hotel_bookings(id SERIAL PRIMARY KEY,customer_id INT REFERENCES hotel_customers(id) NOT NULL,room_id INT REFERENCES hotel_rooms(id) NOT NULL,check_in DATE NOT NULL,check_out DATE NOT NULL,status VARCHAR(55) NOT NULL,total_amount DECIMAL(10,2) NOT NULL,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
                    """);
            System.out.println("All tables are created successfully");
            stmt.close();
        } catch (Exception e) {
            throw e;
        }
    }
}

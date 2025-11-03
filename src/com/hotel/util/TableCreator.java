package com.hotel.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
            defaultData(conn,stmt);
            stmt.close();
        } catch (Exception e) {
            throw e;
        }
    }

    private static void defaultData(Connection conn, Statement stmt) throws SQLException {

        try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM hotel_locations;")) {
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                System.out.println("ℹ️ Sample data already exists. Skipping seeding.");
                return;
            }
        }

        //  default locations
        stmt.executeUpdate("""
            INSERT INTO hotel_locations (city_name, state, country) VALUES
            ('Chennai', 'Tamil Nadu', 'India'),
            ('Bangalore', 'Karnataka', 'India'),
            ('Mumbai', 'Maharashtra', 'India');
        """);

        //  default hotels
        stmt.executeUpdate("""
            INSERT INTO hotels (hotel_name, location_id, type, contact_number, email, address) VALUES
            ('Sea View Resort', 1, 'Resort', '9876543210', 'seaview@hotel.com', 'Beach Road, Chennai'),
            ('Skyline Inn', 2, 'Business', '9123456780', 'skyline@hotel.com', 'MG Road, Bangalore'),
            ('Grand Palace', 3, 'Luxury', '9988776655', 'grandpalace@hotel.com', 'Marine Drive, Mumbai');
        """);

        //  default operators
        stmt.executeUpdate("""
            INSERT INTO hotel_operators (hotel_id, name, email, password, role) VALUES
            (1, 'Ravi Kumar', 'ravi@seaview.com', 'ravi123', 'Manager'),
            (2, 'Sneha Rao', 'sneha@skyline.com', 'sneha123', 'Operator'),
            (3, 'Amit Shah', 'amit@grandpalace.com', 'amit123', 'Manager');
        """);

        System.out.println("default data added successfully");
    }
}




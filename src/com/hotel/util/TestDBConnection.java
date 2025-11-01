package com.hotel.util;
import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            if (conn != null) {
                System.out.println("connected to the database");
            } else {
                System.out.println("connection failed");
            }
        } catch (   Exception e) {
            System.out.println("error not connected to the database," + e.getMessage());
        }

    }
}

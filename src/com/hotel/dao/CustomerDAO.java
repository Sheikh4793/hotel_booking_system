package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends BaseDAO implements IUserDAO<Customer> {


    public CustomerDAO(Connection conn) {
        super(conn);
    }

    @Override
    public int insert(Customer c) throws DataBaseException {
        int id = 0;
        String sql = "INSERT INTO hotel_customers(name, email, password, phone, address) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try {
            try (ResultSet rs = executeQuery(sql, c.getName(), c.getEmail(), c.getPassword(), c.getPhoneNumber(), c.getAddress())) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException("unable to insert customer", e);
        }
        return id;
    }

    public int login(String email, String password)throws DataBaseException{
        String sql = "SELECT id FROM hotel_customers WHERE email = ? AND password = ? ";
        int id = 0;
        try {
            try (ResultSet rs = executeQuery(sql, email, password)) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("hi");
            throw new DataBaseException("unable to logIn customer", e);
        }
        return id;
    }

    @Override
    public boolean update(Customer object) {
        return false;
    }

    @Override
    public boolean delete(Customer object) {
        return false;
    }

    @Override
    public Customer getById(Integer id) {
        return null;
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Customer object) {
        return false;
    }


}

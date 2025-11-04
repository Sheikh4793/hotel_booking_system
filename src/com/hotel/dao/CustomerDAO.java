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

    private Connection connection;

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer insert(Customer customer) throws DataBaseException {
        String query = "INSERT INTO hotel_customers (name, email, password, phone, address) VALUES (?, ?, ?, ?, ?) RETURNING id, name, email, phone, address";
        Customer Customer = null;

        try (ResultSet rs = executeQuery(query, customer.getName(), customer.getEmail(), customer.getPassword(), customer.getPhoneNumber(), customer.getAddress())) {
            if (rs.next()) {
                Customer = new Customer();
                Customer.setCustomerId((rs.getInt("id")));
                Customer.setName(rs.getString("name"));
                Customer.setEmail(rs.getString("email"));
                Customer.setPhoneNumber(rs.getString("phone"));
                Customer.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new DataBaseException("Unable to insert customer", e);
        }

        return Customer;
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
    public List<Customer> getAll(int id) throws DataBaseException {
        return List.of();
    }

    @Override
    public boolean isExists(Customer object) {
        return false;
    }

    public Customer login(String email, String password) throws DataBaseException {
        String query = "SELECT id, name, email, phone, address FROM hotel_customers WHERE email = ? AND password = ?";
        Customer customer = null;

        try (ResultSet rs = executeQuery(query, email, password)) {
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new DataBaseException("Unable to log in customer", e);
        }

        return customer;
    }

}


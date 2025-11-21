package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Customer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends BaseDAO implements IUserDAO<Customer> {


    public CustomerDAO() throws SQLException, ClassNotFoundException, IOException {
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

    @Override
    public Customer register(Customer customer) throws DataBaseException {
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

}


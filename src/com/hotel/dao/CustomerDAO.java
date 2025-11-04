package com.hotel.dao;

import com.hotel.dao.contracts.IBaseDAO;
import com.hotel.model.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerDAO implements IBaseDAO<Customer>{

    public CustomerDAO(Connection connection) {

    }

    @Override
    public boolean insert(Customer object) {
        return false;
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


    public int login(String username,String password){
        return 0;
    }
}

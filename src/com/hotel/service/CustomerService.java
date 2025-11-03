package com.hotel.service;

import com.hotel.dao.contracts.IBaseDAO;
import com.hotel.model.Customer;
import com.hotel.service.contracts.IBaseService;

import java.util.List;

public class CustomerService implements IBaseService<Customer> {

    private final IBaseDAO customerDAO;

    public CustomerService(IBaseDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public boolean insert(Customer customer){
      return  customerDAO.insert(customer);
    }

    public int logIn(String email, String password){
        return customerDAO.logIn(email,password);
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
    public List<Customer> getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Customer object) {
        return false;
    }


}

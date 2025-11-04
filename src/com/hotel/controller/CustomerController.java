package com.hotel.controller;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.model.Customer;
import com.hotel.service.CustomerService;
import com.hotel.service.contracts.IBaseService;
import com.hotel.service.contracts.IUserService;

import java.sql.SQLException;

public class CustomerController {
    private final IUserService<Customer> customerService;
    private int id = 0;
    
    public CustomerController(IUserService<Customer> customerService) {
        this.customerService = customerService;
    }

    public int insert(Customer customer)throws ServiceException{
        id = customerService.insert(customer);
        return id;
    }

    public int login(String email,String password) throws ServiceException {
       id = customerService.logIn(email,password);
        return id;
    }
}

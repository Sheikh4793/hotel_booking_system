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
    
    public CustomerController(IUserService<Customer> customerService) {
        this.customerService = customerService;
    }

    public Customer insert(Customer customer)throws ServiceException{
        return  customerService.insert(customer);
    }

    public Customer login(String email,String password) throws ServiceException {
       return customerService.logIn(email,password);
    }
}

package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.model.Customer;
import com.hotel.service.contracts.IUserService;

public class CustomerController {
    private final IUserService<Customer> customerService;
    
    public CustomerController(IUserService<Customer> customerService) {
        this.customerService = customerService;
    }

    public Customer insert(Customer customer)throws ServiceException{
        return  customerService.register(customer);
    }

    public Customer login(String email,String password) throws ServiceException {
       return customerService.logIn(email,password);
    }
}

package com.hotel.controller;

import com.hotel.model.Customer;
import com.hotel.service.contracts.IBaseService;

public class CustomerController {
    private final IBaseService customerService;
    
    public CustomerController(IBaseService customerService) {
        this.customerService = customerService;
    }

    public boolean insert(Customer customer){
        return customerService.insert(customer);
    }

    public int login(String email,String password){
        if( instanceof ){

        }
        return customerService.logIn(email,password);
    }
}

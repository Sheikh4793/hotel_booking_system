package com.hotel.controller;

import com.hotel.service.contracts.UserSeviceI;

public class CustomerController {
    private final UserSeviceI userSeviceI;
    
    public CustomerController(UserServiceI userServiceI){
        this.userSeviceI = userServiceI;
    }
    
}

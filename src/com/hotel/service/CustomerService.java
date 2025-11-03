package com.hotel.service;

import com.hotel.dao.contracts.UserDAOI;
import com.hotel.service.contracts.UserSeviceI;

public class CustomerService implements UserServiceI {

    private final UserDAOI userDAOI;

    public CustomerService(UserDAOI userDAOI){
        this.userDAOI = userDAOI;
    }

    
}

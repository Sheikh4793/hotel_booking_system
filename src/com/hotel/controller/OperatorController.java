package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.model.Operator;
import com.hotel.service.OperatorService;
import com.hotel.service.contracts.IUserService;

public class OperatorController {

    private final IUserService<Operator> operatorService;

    public OperatorController(IUserService<Operator> operatorService) {
        this.operatorService = operatorService;
    }

    public int login(String email,String password)throws ServiceException {
        return operatorService.logIn(email,password);
    }
}

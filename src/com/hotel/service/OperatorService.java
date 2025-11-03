package com.hotel.service;

import com.hotel.controller.CustomerController;
import com.hotel.dao.OperatorDAO;

public class OperatorService {


    private final OperatorDAO operatorDAO;


    public OperatorService(OperatorDAO operatorDAO){
        this.operatorDAO = operatorDAO;
    }

}

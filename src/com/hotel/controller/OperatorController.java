package com.hotel.controller;

import com.hotel.service.OperatorService;

public class OperatorController {

    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
}

package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.service.HotelService;
import com.hotel.service.contracts.IBaseService;

import java.util.List;

public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    public List<BookingDisplay> getHotelList(int operatorId) throws ServiceException {
       return  hotelService.getHotelBookings(operatorId);
    }

}

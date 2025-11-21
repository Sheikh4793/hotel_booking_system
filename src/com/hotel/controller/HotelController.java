package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.model.Hotel;
import com.hotel.dto.BookingDisplay;
import com.hotel.dto.HotelDisplay;
import com.hotel.service.HotelService;

import java.util.List;

public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    public List<BookingDisplay> getHotelList(int operatorId) throws ServiceException {
       return  hotelService.getHotelBookings(operatorId);
    }

    public List<HotelDisplay> getHotelDetails(int operatorId) throws ServiceException {
        return  hotelService.getHotelDetails(operatorId);
    }

    public List<Hotel> getAllHotels(int id) throws ServiceException {
        return hotelService.getAll(id);
    }

    public List<HotelDisplay> getHotelsByLocation(String location) throws ServiceException {
        return hotelService.getHotelByLocation(location);
    }




}

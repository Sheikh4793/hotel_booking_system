package com.hotel.controller;

import com.hotel.model.Booking;
import com.hotel.service.contracts.IBaseService;

public class BookingController {

    private final IBaseService<Booking> bookingService;

    public BookingController(IBaseService<Booking> bookingService) {
        this.bookingService = bookingService;
    }

}

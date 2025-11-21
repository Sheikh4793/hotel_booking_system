package com.hotel.controller;

import com.hotel.customexception.ServiceException;
import com.hotel.model.Room;
import com.hotel.dto.BookingDisplay;
import com.hotel.service.BookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public List<BookingDisplay> getBookingsByCustomer(int  customerId)throws ServiceException {
        return bookingService.getBookingsByCustomer(customerId);
    }

    public List<Room> getAvailableRooms(int HotelId, LocalDate checkIn, LocalDate checkOut)throws ServiceException {
        return bookingService.getAvailableRooms(HotelId, checkIn, checkOut);
    }

    public boolean bookRooms(int customerId, List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut, BigDecimal total)throws ServiceException {
        return bookingService.bookRooms(customerId, roomIds, checkIn, checkOut,total);
    }

    public boolean cancelBooking(int bookingId) throws ServiceException {
        return bookingService.cancelBooking(bookingId);
    }

    public List<BookingDisplay> getBookingHistory(int customerId){
        return bookingService.getBookingHistory(customerId);
    }

}

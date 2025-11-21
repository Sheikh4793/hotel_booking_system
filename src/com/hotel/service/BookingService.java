package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.BookingDAO;
import com.hotel.model.Room;
import com.hotel.dto.BookingDisplay;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService  {

    private final BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public List<Room> getAvailableRooms(int hotelId, LocalDate checkIn, LocalDate checkOut)throws ServiceException {
        try {
            return bookingDAO.getAvailableRooms(hotelId, checkIn, checkOut);
        }
        catch (SQLException e){
            throw new ServiceException("Error fetching available rooms", e);
        }
    }

    public boolean bookRooms(int customerId, List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut, BigDecimal total)throws ServiceException {
        try {
            return bookingDAO.bookRooms(customerId, roomIds, checkIn, checkOut,total);
        }
        catch (SQLException e){
            throw new ServiceException("Error booking rooms", e);
        }
    }

    public List<BookingDisplay> getBookingsByCustomer(int customerId) throws ServiceException{
        try{
            return bookingDAO.getBookingsByCustomer(customerId);
        }
        catch (SQLException e){
            throw new ServiceException("Error fetching booking rooms", e);
        }
    }

     public boolean cancelBooking(int bookingId) throws ServiceException {
        try{
            return bookingDAO.cancelBooking(bookingId);
        }
        catch (DataBaseException e){
            throw new ServiceException("unable to cancel booking",e);
        }

     }

    public List<BookingDisplay> getBookingHistory(int customerId){
        try{
            return bookingDAO.getBookingHistory(customerId);
        }
        catch (SQLException e){
            throw new ServiceException("Error fetching booking rooms", e);
        }
    }


}


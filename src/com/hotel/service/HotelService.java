package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.HotelDAO;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.dto.HotelDisplay;

import java.util.List;

public class HotelService {

    private final HotelDAO hotelDAO;

    public HotelService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public List<BookingDisplay> getHotelBookings(int operatorId) throws ServiceException {
        try {
            return hotelDAO.getHotelBookings(operatorId);
        } catch (DataBaseException e) {
            throw new ServiceException("unable to get hotelBookings", e);
        }
    }

    public List<HotelDisplay> getHotelDetails(int operatorId) throws ServiceException {
        try {
            return hotelDAO.getHotelDetails(operatorId);
        } catch (DataBaseException e) {
            throw new ServiceException("unable to get hotelDetails", e);
        }
    }

    public List<HotelDisplay> getHotelByLocation(String location) throws ServiceException {

        try {
            return hotelDAO.getHotelByLocation(location);
        } catch (DataBaseException e) {
            throw new ServiceException("unable to get hotelByLocation", e);
        }
    }


    public List<Hotel> getAll(int id) throws ServiceException {
        try {
            return hotelDAO.getAll(id);
        } catch (DataBaseException e) {
            throw new ServiceException("unable to get hotels", e);
        }
    }


}

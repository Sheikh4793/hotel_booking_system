package com.hotel.service.contracts;

import com.hotel.customexception.ServiceException;
import com.hotel.model.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.model.dto.HotelDisplay;

import java.util.List;

public interface IHotelService extends IBaseService<Hotel> {
    List<BookingDisplay> getHotelBookings(int operatorId) throws ServiceException;
    List<HotelDisplay> getHotelDetails(int operatorId) throws ServiceException;
    List<HotelDisplay> getHotelByLocation(String location) throws ServiceException;

}
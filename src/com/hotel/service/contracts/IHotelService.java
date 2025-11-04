package com.hotel.service.contracts;

import com.hotel.customexception.ServiceException;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;

import java.util.List;

public interface IHotelService extends IBaseService<Hotel> {
    List<BookingDisplay> getHotelBookings(int operatorId) throws ServiceException;

}
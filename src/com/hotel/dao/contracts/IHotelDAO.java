package com.hotel.dao.contracts;

import com.hotel.customexception.DataBaseException;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;

import java.util.List;

public interface IHotelDAO extends IBaseDAO<Hotel>{
    List<BookingDisplay> getHotelBookings(int operatorId)throws DataBaseException;
}

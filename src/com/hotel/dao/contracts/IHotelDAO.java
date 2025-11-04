package com.hotel.dao.contracts;

import com.hotel.customexception.DataBaseException;
import com.hotel.model.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.model.dto.HotelDisplay;

import java.util.List;

public interface IHotelDAO extends IBaseDAO<Hotel>{
    List<BookingDisplay> getHotelBookings(int operatorId)throws DataBaseException;
    List<HotelDisplay>   getHotelDetails(int operatorId)throws DataBaseException;
    List<HotelDisplay> getHotelByLocation(String location) throws DataBaseException;
}

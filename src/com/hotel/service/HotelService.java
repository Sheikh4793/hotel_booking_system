package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IHotelDAO;
import com.hotel.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.service.contracts.IHotelService;

import java.util.List;

public class HotelService implements IHotelService {

    private IHotelDAO hotelDAO;

    public HotelService(IHotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override
    public List<BookingDisplay> getHotelBookings(int operatorId) throws ServiceException {
        try{
          return  hotelDAO.getHotelBookings(operatorId);
        }
        catch(DataBaseException e){
            throw new ServiceException("unable to get hotelBookings",e);
        }
    }

    @Override
    public int insert(Hotel object) {
        return 0;
    }

    @Override
    public boolean update(Hotel object) {
        return false;
    }

    @Override
    public boolean delete(Hotel object) {
        return false;
    }

    @Override
    public Hotel getById(Integer id) {
        return null;
    }

    @Override
    public List<Hotel> getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Hotel object) {
        return false;
    }
}

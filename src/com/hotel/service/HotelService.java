package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IHotelDAO;
import com.hotel.model.dto.BookingDisplay;
import com.hotel.model.Hotel;
import com.hotel.model.dto.HotelDisplay;
import com.hotel.service.contracts.IHotelService;

import java.util.List;

public class HotelService implements IHotelService {

    private final IHotelDAO hotelDAO;

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

    public List<HotelDisplay> getHotelDetails(int operatorId) throws ServiceException {
        try{
            return hotelDAO.getHotelDetails(operatorId);
        }
        catch (DataBaseException e){
            throw new ServiceException("unable to get hotelDetails",e);
        }
    }

    @Override
    public List<HotelDisplay> getHotelByLocation(String location) throws ServiceException {

        try{
            return hotelDAO.getHotelByLocation(location);
        }
        catch (DataBaseException e){
            throw new ServiceException("unable to get hotelByLocation",e);
        }
    }

    @Override
    public Hotel insert(Hotel object) {
        return null;
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
    public List<Hotel> getAll(int id) throws ServiceException {
        try {
            return hotelDAO.getAll(id);
        }
        catch (DataBaseException e){
            throw new ServiceException("unable to get hotels",e);
        }
    }


    @Override
    public boolean isExists(Hotel object) {
        return false;
    }


}

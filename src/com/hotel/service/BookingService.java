package com.hotel.service;

import com.hotel.dao.contracts.IBaseDAO;
import com.hotel.model.Booking;
import com.hotel.service.contracts.IBaseService;

import java.util.List;

public class BookingService implements IBaseService<Booking> {

    private final IBaseDAO<Booking> bookingDAO;

    public BookingService(IBaseDAO<Booking> bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public Booking insert(Booking object) {
        return null;
    }

    @Override
    public boolean update(Booking object) {
        return false;
    }

    @Override
    public boolean delete(Booking object) {
        return false;
    }

    @Override
    public Booking getById(Integer id) {
        return null;
    }

    @Override
    public List<Booking> getAll(int id) {
        return List.of();
    }

    @Override
    public boolean isExists(Booking object) {
        return false;
    }
}

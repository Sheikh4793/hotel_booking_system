package com.hotel.dao;

import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IBaseDAO;
import com.hotel.model.Booking;

import java.sql.Connection;
import java.util.List;

public class BookingDAO extends BaseDAO implements IBaseDAO<Booking> {

    private Connection connection;

    public BookingDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Booking insert(Booking obj) throws DataBaseException {
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
    public List<Booking> getAll(int id) throws DataBaseException {
        return List.of();
    }

    @Override
    public boolean isExists(Booking object) {
        return false;
    }
}

package com.hotel.dao;

import com.hotel.dao.contracts.IBaseDAO;

import java.util.List;

public class OperatorDAO implements IBaseDAO {

    @Override
    public boolean insert(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public Object getById(Integer id) {
        return null;
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Object object) {
        return false;
    }
}

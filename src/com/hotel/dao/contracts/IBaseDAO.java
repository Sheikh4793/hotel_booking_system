package com.hotel.dao.contracts;

import com.hotel.customexception.DataBaseException;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {
    T insert(T obj) throws DataBaseException;
    boolean update(T object);
    boolean delete(T object);
    T getById(Integer id);
    List<T> getAll(int id)throws DataBaseException;
    boolean isExists(T object);
}

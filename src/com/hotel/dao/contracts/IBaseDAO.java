package com.hotel.dao.contracts;

import java.util.List;

public interface IBaseDAO<T> {
    boolean insert(T object);
    boolean update(T object);
    boolean delete(T object);
    T getById(Integer id);
    List<T> getAll();
    boolean isExists(T object);
}

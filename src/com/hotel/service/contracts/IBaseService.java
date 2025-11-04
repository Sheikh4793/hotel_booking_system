package com.hotel.service.contracts;

import java.util.List;

public interface IBaseService<T>     {
    int insert(T object);
    boolean update(T object);
    boolean delete(T object);
    T getById(Integer id);
    List<T> getAll();
    boolean isExists(T object);
}

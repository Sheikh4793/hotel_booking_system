package com.hotel.service.contracts;

import java.util.List;

public interface IBaseService<T>     {
    T insert(T object);
    boolean update(T object);
    boolean delete(T object);
    T getById(Integer id);
    List<T> getAll(int id);
    boolean isExists(T object);
}

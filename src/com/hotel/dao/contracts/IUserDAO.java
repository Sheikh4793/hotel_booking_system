package com.hotel.dao.contracts;

import com.hotel.customexception.DataBaseException;
import com.hotel.model.Customer;

public interface IUserDAO<T>  {
    T login(String email,String password)throws DataBaseException;

    T register(T customer)throws DataBaseException;
}

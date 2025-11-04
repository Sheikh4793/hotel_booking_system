package com.hotel.dao.contracts;

import com.hotel.customexception.DataBaseException;
import com.hotel.model.Customer;

public interface IUserDAO<T> extends IBaseDAO<T> {
    int login(String email,String password)throws DataBaseException;
}

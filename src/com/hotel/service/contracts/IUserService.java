package com.hotel.service.contracts;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.model.User;

public interface IUserService<T> extends IBaseService<T> {
    T logIn(String email,String password)throws ServiceException;
}

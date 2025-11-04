package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Customer;
import com.hotel.service.contracts.IUserService;

import java.util.List;

public class CustomerService implements IUserService<Customer> {

    private final IUserDAO<Customer> customerDAO;
    private int userId = 0;

    public CustomerService(IUserDAO<Customer> customerDAO){
        this.customerDAO = customerDAO;
    }

    public int insert(Customer customer)throws ServiceException{
      try {
          userId = customerDAO.insert(customer);
      }
      catch(DataBaseException e){
          throw new ServiceException("unable to insert customer",e);
      }
      return userId;
    }

    public int logIn(String email, String password)throws ServiceException{
        try {
            userId = customerDAO.login(email, password);
        }
        catch (DataBaseException e) {
            throw new ServiceException("unable to logIn customer ",e);
        }
        return userId;

    }

    @Override
    public boolean update(Customer object) {
        return false;
    }

    @Override
    public boolean delete(Customer object) {
        return false;
    }

    @Override
    public Customer getById(Integer id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Customer object) {
        return false;
    }


}

package com.hotel.service;

import com.hotel.customexception.DataBaseException;
import com.hotel.customexception.ServiceException;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Operator;
import com.hotel.service.contracts.IUserService;

import java.util.List;

public class OperatorService implements IUserService<Operator> {


    private final IUserDAO<Operator> operatorDAO;
    private int userId = 0;


    public OperatorService(IUserDAO<Operator> operatorDAO){
        this.operatorDAO = operatorDAO;
    }

    @Override
    public int logIn(String email, String password) throws ServiceException {
        try {
           userId = operatorDAO.login(email, password);
        }
        catch (DataBaseException e) {
            throw new ServiceException("unable to logIn operator",e);
        }
        return userId;
    }

    @Override
    public int insert(Operator object) {
        return 0;
    }

    @Override
    public boolean update(Operator object) {
        return false;
    }

    @Override
    public boolean delete(Operator object) {
        return false;
    }

    @Override
    public Operator getById(Integer id) {
        return null;
    }

    @Override
    public List<Operator> getAll() {
        return List.of();
    }

    @Override
    public boolean isExists(Operator object) {
        return false;
    }
}

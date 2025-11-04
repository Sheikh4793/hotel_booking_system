package com.hotel.dao;


import com.hotel.customexception.DataBaseException;
import com.hotel.dao.base.BaseDAO;
import com.hotel.dao.contracts.IUserDAO;
import com.hotel.model.Operator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperatorDAO extends BaseDAO implements IUserDAO<Operator> {

    private Connection connection;

    public OperatorDAO(Connection connection) {
        super(connection);
    }


    @Override
    public Operator insert(Operator obj) throws DataBaseException {
        return null;
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
    public List<Operator> getAll(int id) throws DataBaseException {
        return List.of();
    }


    @Override
    public boolean isExists(Operator object) {
        return false;
    }


    @Override
    public Operator login(String email, String password) throws DataBaseException {
            String sql = "SELECT id, hotel_id, name, role FROM hotel_operators WHERE email = ? AND password = ?";
            Operator operator = null;

            try (ResultSet rs = executeQuery(sql, email, password)) {
                if (rs.next()) {
                    operator = new Operator();
                    operator.setOperatorId(rs.getInt("id"));
                    operator.setHotelId(rs.getInt("hotel_id"));
                    operator.setName(rs.getString("name"));
                    operator.setRole(rs.getString("role"));
                }
            } catch (SQLException e) {
                throw new DataBaseException("Unable to log in operator in DB", e);
            }

            return operator;
        }

    }


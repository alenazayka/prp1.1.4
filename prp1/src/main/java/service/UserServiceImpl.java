package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import exception.DBException;
import model.User;
import util.UserDaoFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl INSTANCE;
    private List<User> list = new ArrayList<>();
  //private UserJdbcDAO dao = UserJdbcDAO.getInstance();
 // private UserDAO dao = UserHibernateDAO.getInstance();
    private UserDAO dao = UserDaoFactory.getUserDAO();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new UserServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            List<User> list = dao.getAllUsers();
            return list;
        } catch (DBException t) {
            return Collections.emptyList();
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            return dao.getUserById(id);
        } catch (DBException t) {
            return null;
        }
    }

    @Override
    public boolean updateUser(int id, String name, int age) {
        try {
            dao.updateUser(id, name, age);
            return true;
        } catch (DBException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUserById(int id) {
        try{
            dao.deleteUserById(id);
            return true;
        } catch(DBException e){
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            dao.addUser(user);
            return true;
        } catch (SQLException | DBException e) {
            return false;
        }
    }
}

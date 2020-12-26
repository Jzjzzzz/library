package service.impl;

import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao=new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.findUserByUsernameAndPassword(username,password);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Boolean add(User user) {
        return userDao.add(user);
    }

    @Override
    public User finById(int id) {
        return userDao.findById(id);
    }

    @Override
    public Boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public Boolean delete(int id) {
        return userDao.delete(id);
    }
}

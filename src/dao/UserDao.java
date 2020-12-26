package dao;

import domain.Book;
import domain.User;

import java.util.List;

public interface UserDao {
    User findUserByUsernameAndPassword(String username, String password);

    Boolean add(User user);


    List<User> findAll();

    Boolean updateUId(User user);

    Boolean updateBorrow(User user);

    User findById(int id);

    Boolean update(User user);

    Boolean delete(int id);

    Boolean borrowBooks(int id,User user);

}

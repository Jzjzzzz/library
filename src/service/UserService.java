package service;

import domain.Book;
import domain.User;

import java.util.List;

public interface UserService {
    /*
    * 用户登录
    * */
    User login(String username,String password);

    /*
     * 查询所有用户
     * */
    List<User> findAll();

    /*
     * 添加用户
     * */
    Boolean add(User user);

    /*
     * 根据id查询用户
     * */
    User finById(int id);

    /*
     * 修改用户
     * */
    Boolean update(User user);

    Boolean delete(int id);
}

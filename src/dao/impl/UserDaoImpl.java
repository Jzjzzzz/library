package dao.impl;

import dao.UserDao;
import domain.Book;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
       try{
           String sql="select * from user where username=? and password = ?";
           User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
           return user;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public Boolean add(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql="select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public Boolean updateUId(User user) {
        String sql = "update user set bookNumber=? where id=?";
        int result = template.update(sql, 1,user.getId());
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean updateBorrow(User user) {
        String sql = "update user set bookNumber=? where id=?";
        int result = template.update(sql, 0,user.getId());
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User findById(int id) {
        String sql="select * from user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public Boolean update(User user) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        String sql="delete from user where id=?";
        int result = template.update(sql, id);
        if(result==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean borrowBooks(int id, User user) {
        return null;
    }
}

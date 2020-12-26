package dao.impl;

import dao.BorrowDao;
import domain.Book;
import domain.Borrow;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Date;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Borrow> findAll(int uid) {
        String sql = "select * from borrow where userid=?";
        List<Borrow> borrows=null;
        try {
            borrows = template.query(sql, new BeanPropertyRowMapper<Borrow>(Borrow.class),uid);

        }catch (Exception e){
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public List<Borrow> findAll() {
        String sql = "select * from borrow ";
        List<Borrow> borrows=null;
        try {
            borrows = template.query(sql, new BeanPropertyRowMapper<Borrow>(Borrow.class));

        }catch (Exception e){
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public Borrow findById(int id) {
        String sql = "select * from borrow where id=?";
        Borrow borrow=null;
        try {
            borrow  = template.queryForObject(sql, new BeanPropertyRowMapper<Borrow>(Borrow.class), id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return borrow;
    }


    @Override
    public Boolean giveBack(int id) {
        String sql = "update borrow set isRepay=?,repayDate=? where id=?";
        int result = template.update(sql, 1,new Date(),id);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
}

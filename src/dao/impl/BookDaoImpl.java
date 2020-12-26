package dao.impl;

import dao.BookDao;
import domain.Book;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import util.JDBCUtils;

import javax.sql.RowSet;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Book> findAll() {
        String sql = "select * from books";
        List<Book> books=null;
        try {
            books = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));

        }catch (Exception e){
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Boolean add(Book book) {
        String sql = "insert into books values(null,?,?,?,?,?,?,?)";
        int result = template.update(sql, book.getBookName(), book.getDescription(), book.getAuthor(), book.getPrice(), book.getAmount()
                , book.getAmount(), new Date());
        if (result == 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Book findById(int id) {
        String sql = "select * from books where id=?";
        Book book=null;
        try {
            book = template.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book findByName(String bookname) {
        String sql="select * from books where bookName=?";
        Book book=null;
        try {
            book = template.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), bookname);
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void reduce(Book book) {
        String sql = "update books set surplusAmount=? where id=?";
        int result = template.update(sql,book.getSurplusAmount()-1,book.getId());

    }

    @Override
    public void addreduce(Book book) {
        String sql = "update books set surplusAmount=? where id=?";
        int result = template.update(sql,book.getSurplusAmount()+1,book.getId());
    }

    @Override
    public Boolean update(Book book) {
        String sql = "update books set bookName=?,surplusAmount=?,author=?,price=?,description=? where id=?";
        int result = template.update(sql, book.getBookName(), book.getSurplusAmount(), book.getAuthor(), book.getPrice(), book.getDescription(), book.getId());
        if (result == 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Boolean delete(int id) {
        String sql = "delete from books where id=?";
        int result = template.update(sql, id);
        if (result == 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Boolean borrowBook(Book book, User user) {
        String sql = "insert into borrow values(null,?,?,?,?,?,?,?)";
        int result = template.update(sql, book.getId(), user.getId(), user.getUsername(), book.getBookName(), new Date()
                , 0, null);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }


}

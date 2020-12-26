package dao;

import domain.Book;
import domain.User;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public interface BookDao {
    List<Book> findAll();

    Boolean add(Book book);

    Book findById(int id);

    Book findByName(String bookname);

    void reduce(Book book);

    void addreduce(Book book);


    Boolean update(Book book);

    Boolean delete(int id);

    Boolean borrowBook(Book book, User user);
}

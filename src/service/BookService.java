package service;

import domain.Book;
import domain.User;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public interface BookService {
     /*
     * 查询所有书籍
     * */
     List<Book> findAll();


     /*
     * 添加书籍
     * */
     Boolean add(Book book);

     /*
     * 根据id查询书本
     * */
     Book finById(int id);

     /*
     * 根据书籍名称查询是否有该书籍
     * */
     Book finByName(String bookname);


     /*
     * 修改书籍
     * */
     Boolean update(Book book);

     Boolean delete(int id);

     /*
     * 借阅书籍
     * */
     Boolean borrowBook(Book book, User user);


}

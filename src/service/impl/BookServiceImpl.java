package service.impl;

import dao.impl.BookDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Book;
import domain.User;
import service.BookService;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class BookServiceImpl implements BookService {
    private BookDaoImpl bookDao=new BookDaoImpl();
    private UserDaoImpl userService=new UserDaoImpl();
    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Boolean add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book finById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public Book finByName(String bookname) {
        return bookDao.findByName(bookname);
    }

    @Override
    public Boolean update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public Boolean delete(int id) {
        return bookDao.delete(id);
    }

    @Override
    public Boolean borrowBook(Book book, User user) {
        Boolean result = bookDao.borrowBook(book, user); //把书籍和用户实体传进去生成借阅记录
        Boolean userResult = userService.updateUId(user);//修改用户是否借书状态
        if(result==true&&userResult==true){
            bookDao.reduce(book); //当生成成功后对该书籍库存执行-1操作
        }
        return result;
    }
}

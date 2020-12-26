package service;

import domain.Book;
import domain.Borrow;
import domain.User;

import java.util.List;

public interface BorrowService {
    /*
    * 查询当前用户的所有借阅记录
    * */
    List<Borrow> findAll(User user);

    /*
    * 查询所有用户的所有借阅记录
    * */
    List<Borrow> findAll();

    /*
    * 归还图书
    * */
    Boolean giveBack(int id,User user);

}

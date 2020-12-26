package service.impl;

import dao.impl.BookDaoImpl;
import dao.impl.BorrowDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Book;
import domain.Borrow;
import domain.User;
import service.BorrowService;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    private BorrowDaoImpl borrowDao=new BorrowDaoImpl();
    private BookDaoImpl bookDao=new BookDaoImpl();
    private UserDaoImpl userDao=new UserDaoImpl();
    @Override
    public List<Borrow> findAll(User user) {
        return borrowDao.findAll(user.getId());
    }

    @Override
    public List<Borrow> findAll() {
        return borrowDao.findAll();
    }


    @Override
    public Boolean giveBack(int id,User user) {
        Boolean result = borrowDao.giveBack(id); //还书操作
        Borrow borrow = borrowDao.findById(id); //获取该借书model
        Book book = bookDao.findById(borrow.getBookid());//通过id查询书籍
        Boolean updateResult = userDao.updateBorrow(user);
        if(result==true && book!=null&&updateResult==true){
            bookDao.addreduce(book); //当借书记录修改成功并且获得书籍实体时对书籍库存执行+1操作
            return true;
        }else{
            return false;
        }

    }
}

package dao;

import domain.Book;
import domain.Borrow;

import java.util.List;

public interface BorrowDao {
    List<Borrow> findAll(int id);

    List<Borrow> findAll();

    Borrow findById(int id);

    Boolean giveBack(int id);
}

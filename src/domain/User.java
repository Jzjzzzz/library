package domain;

import java.util.Date;


/*
* 用户Model
* */
public class User {

    private Integer id; //主键
    private String username; //用户名
    private String password; //用户密码
    private Integer bookNumber; //借书次数
    private Date newBorrowBooksCreate; //最近一次借书时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Date getNewBorrowBooksCreate() {
        return newBorrowBooksCreate;
    }

    public void setNewBorrowBooksCreate(Date newBorrowBooksCreate) {
        this.newBorrowBooksCreate = newBorrowBooksCreate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bookNumber=" + bookNumber +
                ", newBorrowBooksCreate=" + newBorrowBooksCreate +
                '}';
    }
}

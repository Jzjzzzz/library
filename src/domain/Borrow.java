package domain;

import java.util.Date;

public class Borrow {
    private int id; //主键id
    private int bookid; //书籍id
    private int userid; //用户id
    private String username; //用户名称
    private String bookName; //书籍名称
    private int isRepay; //是否已归还
    private Date createDate; //借阅时间
    private Date repayDate; //归还时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getIsRepay() {
        return isRepay;
    }

    public void setIsRepay(int isRepay) {
        this.isRepay = isRepay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", bookid=" + bookid +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", bookName='" + bookName + '\'' +
                ", isRepay=" + isRepay +
                ", createDate=" + createDate +
                ", repayDate=" + repayDate +
                '}';
    }
}

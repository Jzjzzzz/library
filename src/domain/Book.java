package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private Integer id; //主键
    private String bookName; //书籍名称
    private String description; //书籍简介
    private String author; //书籍作者
    private BigDecimal price; //书籍价格
    private Integer amount; //书籍数量
    private Integer surplusAmount; //剩余数量
    private Date createDate;//录入时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Integer surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", surplusAmount=" + surplusAmount +
                ", createDate=" + createDate +
                '}';
    }
}

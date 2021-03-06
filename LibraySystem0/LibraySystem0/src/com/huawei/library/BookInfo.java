package com.huawei.library;

import com.huawei.exam.BookStatusEnum;

/**
 * <p>Title: 考生可以根据自己的需求在本类中增加方法和变量，但不能修改已有方法与变量 </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BookInfo {
    private String bookName; //图书名称
    private int price; //图书价格
    private BookStatusEnum status = BookStatusEnum.BOOK_IDLE; //图书状态,初始为空闲状态
    private String userName; //借书人，当图书状态为空闲/报废时，赋值为空字符串
    private int borrowDays; //本次被预借天数，当图书状态为空闲/报废时,赋值为0
    private int totalDays; //图书被借阅累计总天数，当图书状态为借出/挂失时,赋值为9999

    public BookInfo() {
    }

    public BookInfo(String bookName, int price) {
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public int getPrice() {
        return price;
    }

    public int getBorrowDays() {
        return borrowDays;
    }

    public BookStatusEnum getStatus() {
        return status;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public String getUserName() {
        return userName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBorrowDays(int borrowDays) {
        this.borrowDays = borrowDays;
    }

    public void setStatus(BookStatusEnum status) {
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
}

package com.huawei.library;

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
public class UserInfo {
    private String userName; //用户名
    private String password; //密码
    private int balance; //余额
    private int credit = 5; //信用值初始为5
    private int bookNum; //借书的数量
    private String books[] = new String[3]; //借书的书名,最多借3本

    public UserInfo() {
    }

    public UserInfo(String userName, String password, int balance) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public int getBookNum() {
        return bookNum;
    }

    public String[] getBooks() {
        return books;
    }

    public int getCredit() {
        return credit;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}

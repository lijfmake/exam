package com.huawei.library;

/**
 * <p>Title: �������Ը����Լ��������ڱ��������ӷ����ͱ������������޸����з�������� </p>
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
    private String userName; //�û���
    private String password; //����
    private int balance; //���
    private int credit = 5; //����ֵ��ʼΪ5
    private int bookNum; //���������
    private String books[] = new String[3]; //���������,����3��

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

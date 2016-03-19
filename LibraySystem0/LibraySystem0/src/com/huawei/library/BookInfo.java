package com.huawei.library;

import com.huawei.exam.BookStatusEnum;

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
public class BookInfo {
    private String bookName; //ͼ������
    private int price; //ͼ��۸�
    private BookStatusEnum status = BookStatusEnum.BOOK_IDLE; //ͼ��״̬,��ʼΪ����״̬
    private String userName; //�����ˣ���ͼ��״̬Ϊ����/����ʱ����ֵΪ���ַ���
    private int borrowDays; //���α�Ԥ����������ͼ��״̬Ϊ����/����ʱ,��ֵΪ0
    private int totalDays; //ͼ�鱻�����ۼ�����������ͼ��״̬Ϊ���/��ʧʱ,��ֵΪ9999

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
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
    private int rent;
    private int actualRent;
    
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
    
    
    public int getRent() {
        return rent;
    }

    public int getActualRent() {
        return actualRent;
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
    public void setRent(int days) {
      	if(price>=100){
    		if(totalDays+days <=90)
    			rent=5*days;
    		else if(totalDays<90)
    			rent=(90-totalDays)*5+(days-90+totalDays)*3;
    		else
    			rent=days*3;
    	}
    	else if(price>=50){
    		if(totalDays+days <=90)
    			rent=3*days;
    		else if(totalDays<90)
    			rent=(90-totalDays)*3+(days-90+totalDays)*2;
    		else
    			rent=days*2;
    	}
    	else{
    		rent=days;
    	}
    }
    public void setActualRent(int days) {
     	if(price>=100){
    		if(totalDays+days <=90)
    			actualRent=5*days;
    		else if(totalDays<90)
    			actualRent=(90-totalDays)*5+(days-90+totalDays)*3;
    		else
    			actualRent=days*3;
    	}
    	else if(price>=50){
    		if(totalDays+days <=90)
    			actualRent=3*days;
    		else if(totalDays<90)
    			actualRent=(90-totalDays)*3+(days-90+totalDays)*2;
    		else
    			actualRent=days*2;
    	}
    	else{
    		actualRent=days;
    	}
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

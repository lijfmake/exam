package com.huawei.library;

import java.awt.print.Book;
import java.util.*;

import com.huawei.exam.BookStatusEnum;
import com.huawei.exam.ReturnCodeEnum;

import sun.org.mozilla.javascript.internal.ast.NewExpression;

/**
 * <p>Title: ������ʵ����</p>
 * ��������Ҫ�󷵻أ���������װ�������
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
public class LibraryImpl {
	
    /**
     * ����ȫ�ֱ���
     *
    */
	
	Map<String, UserInfo> userMap;
	Map<String, BookInfo> bookMap;
	UserInfo currentUser;

    // �����ṩ�޲������캯�����������ں������и�����Ҫ���ӳ�ʼ������
    // ������л���ֻ������һ��LibraryImplʵ��������������������������һֱʹ�����ʵ��
    public LibraryImpl() {
    }

    /**
     * ������Ҫʵ�ֵĽӿ�
     * initial����ӿڣ�ʵ��ϵͳ��ʼ������
     * ���������ʵ�����ļ��Ķ�ȡ����ȡ��Ľ�����������뱾����
     *
     * @param users UserInfo[]���ļ���ȡ���û���Ϣ
     * @param books BookInfo[]���ļ���ȡ��ͼ����Ϣ
     *
     * @return OpResult����������ͨ��OpResult������createOpResult����������Ҫ��OpResult����
     */
    public OpResult opInit(UserInfo[] users, BookInfo[] books) {
    	userMap=new HashMap<String, UserInfo>();
    	bookMap=new HashMap<String, BookInfo>();
    	//���û������ڴ�
    	for(int i=0;i<users.length;i++){
    		userMap.put(users[i].getUserName(), users[i]);
    	}
    	//���鱾�����ڴ�
    	for(int i=0;i<books.length;i++){
    		bookMap.put(books[i].getBookName(), books[i]);
    	}
    	currentUser = null;
        return OpResult.createOpResult(ReturnCodeEnum.E000);
    }

    /**
     * ������Ҫʵ�ֵĽӿ�
     * login����ӿڣ�ʵ���û���¼����
     *
     * @param user String���û���
     * @param password String������
     *
     * @return OpResult����������ͨ��OpResult������createOpResult����������Ҫ��OpResult����
     */
    public OpResult opLogin(String userName, String password) {
    	
    	if(userMap.containsKey(userName) && userMap.get(userName).getPassword().equals(password)){
    		currentUser=userMap.get(userName);
    		return OpResult.createOpResult(ReturnCodeEnum.E002);
    	}
    	else{
    		return OpResult.createOpResult(ReturnCodeEnum.E001);
    	}
    }

    /**
     * ������Ҫʵ�ֵĽӿ�
     * query book����ӿڣ�ʵ��ͼ���ѯ����
     *
     * @param bookName String��ͼ����
     *
     * @return OpResult����������ͨ��OpResult������createOpResult����������Ҫ��OpResult����
     */
    public OpResult opQueryBook(String bookName) {
    	
    	if(!bookMap.containsKey(bookName))
    	{	//E003:��ѯͼ��ʧ��
    		return OpResult.createOpResult(ReturnCodeEnum.E003);
    	}
    	BookInfo book = bookMap.get(bookName);
    	if(book.getStatus() == BookStatusEnum.BOOK_IDLE){
    		//�鼮���ڿ���״̬
    		book.setUserName("");
    		book.setBorrowDays(0);
    		return OpResult.createOpResult(book);
    	}
    	if(book.getStatus() == BookStatusEnum.BOOK_BORROWED)
    	{
    		//������ʾ��book����
    		BookInfo bookInfoVO = this.createBookInfoVO(book);
    		return OpResult.createOpResult(bookInfoVO);
    	}
    	if(book.getStatus() == BookStatusEnum.BOOK_DISUSE)
    	{
    		return OpResult.createOpResult(book);
    	}
        return OpResult.createOpResult(ReturnCodeEnum.E999);
    }

	public BookInfo createBookInfoVO(BookInfo book) {
		BookInfo bookInfoVO=new BookInfo(book.getBookName(),book.getPrice());
		bookInfoVO.setStatus(BookStatusEnum.BOOK_BORROWED);
		bookInfoVO.setUserName(book.getUserName());
		bookInfoVO.setBorrowDays(book.getBorrowDays());
		bookInfoVO.setTotalDays(9999);
		return bookInfoVO;
	}

	 /**
	  * 
	  * @param user
	  */

    
    public OpResult opQueryUser(String userName) {
    	if(!userMap.containsKey(userName))
    	{ //�û�������
    		return OpResult.createOpResult(ReturnCodeEnum.E004);
    	}
    	//����Ա��Ȩ
    	
    	if(currentUser.getUserName().equals("admin")){
    		UserInfo user = userMap.get(userName);
    		Arrays.sort(user.getBooks());
        	//sortBookName(user);
        	return OpResult.createOpResult(user);
    	}
    	
    	if(!userName.equals(currentUser.getUserName()))
    	{  //�û���ѯ�Ĳ����Լ���״̬
    		return OpResult.createOpResult(ReturnCodeEnum.E004);
    	}
    	//UserInfo user=userMap.get(userName);
    	Arrays.sort(currentUser.getBooks());
    	//sortBookName(user);
    	return OpResult.createOpResult(currentUser);
    }

    /**
     * ������Ҫʵ�ֵĽӿ�
     * borrow����ӿڣ�ʵ�ֽ��鹦��
     *
     * @param bookName String��ͼ����
     * @param days int��Ԥ������
     *
     * @return OpResult����������ͨ��OpResult������createOpResult����������Ҫ��OpResult����
     */
    public OpResult opBorrowBook(String bookName, int days) {
    	
    	
    	if(!bookMap.containsKey(bookName))
    	{//6.ͼ�鲻����
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//�����û�ԭ���ܽ���(1-4)
    	if(currentUser==null)
    	{//�û�δ��¼
    		 return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//UserInfo user=currentUser;
    	BookInfo book=bookMap.get(bookName);
    	if(currentUser.getBookNum()>=3 ||
    			currentUser.getSumFee()+book.getPrice() >300 ||
    			currentUser.getCredit()<=currentUser.getBookNum())
    	{
    		//1.ÿ���û������Խ�3����2.�ҽ����ԭ���ܶ�ܴ���300Ԫ��
    		//3.���ö�ȴ����ѽ�ͼ������ʱ���ɽ���
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//�������
    	book.setRent(days);
    	//int rent=book.getRent();
    	if(currentUser.getBalance()<book.getRent()) 
    	{//4.����ʱ���ܽ���
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//����ͼ��ԭ���ܽ���
    	if(book.getStatus()!=BookStatusEnum.BOOK_IDLE)
    	{//5.ͼ�鲻����
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//����ɹ���ͼ�鴦�ڽ��״̬
    	book.setStatus(BookStatusEnum.BOOK_BORROWED);
    	book.setUserName(currentUser.getUserName());
    	book.setBorrowDays(days);
    	currentUser.setBalance(currentUser.getBalance()-book.getRent());
    	currentUser.setBookNum(currentUser.getBookNum()+1);
    	String borrowedBooks[] = currentUser.getBooks();
    	borrowedBooks[currentUser.getBookNum()-1]=bookName;
    	currentUser.setBooks(borrowedBooks);
    	currentUser.setSumFee(currentUser.getSumFee()+book.getPrice());
        return OpResult.createOpResult(ReturnCodeEnum.E006);
    }

    /**
     * ������Ҫʵ�ֵĽӿ�
     * return����ӿڣ�ʵ�ֻ��鹦��
     *
     * @param bookName String��ͼ����
     * @param days int��ʵ������
     *
     * @return OpResult����������ͨ��OpResult������createOpResult����������Ҫ��OpResult����
     */
    public OpResult opReturnBook(String bookName, int days) {
    	
    	
    	if(currentUser==null) 
    	{  //�û�δ��¼,���ܻ���
   		 	return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	if(!bookMap.containsKey(bookName)) 
    	{//ͼ�鲻����
    		return OpResult.createOpResult(ReturnCodeEnum.E007);
    	}
    	BookInfo book = bookMap.get(bookName);
    	if(book.getStatus()!=BookStatusEnum.BOOK_BORROWED)
    	{//ͼ�鲻�ڽ��״̬
    		return OpResult.createOpResult(ReturnCodeEnum.E007);
    	}
    	book.setTotalDays(book.getTotalDays()+days);
    	book.setStatus(BookStatusEnum.BOOK_IDLE);
    	if(book.getTotalDays()>=300) 
    	{//�鼮�Ƿ񱨷�
    		book.setStatus(BookStatusEnum.BOOK_DISUSE);
    	}
    	
    	UserInfo user=userMap.get(book.getUserName());
    	if(days>book.getBorrowDays())
    	{   //ʵ�ʽ�����������Ԥ������,����-1
    		user.setCredit(user.getCredit()-1);
    	}
    	else if(days<book.getBorrowDays())
    	{  //ʵ�ʽ�������С��Ԥ������,���ز���rent
    		book.setActualRent(days);
    		int actualRent=book.getActualRent();
    		int rent=book.getRent();
    		user.setBalance(user.getBalance()+rent-actualRent);
    	}
    	book.setBorrowDays(0);
    	book.setUserName("");
    	
    	
    	
    	//ɾ���黹���鼮
    	String books[]=user.getBooks();  	
    	String newbooks[] = new String[3];
    	for (int i =0,j = 0;i<books.length;i++) {
			if(bookName!=books[i]){
				newbooks[j]=books[i];
				j++;
			}
			
		}   	
    	user.setBooks(newbooks);
    	user.setBookNum(user.getBookNum()-1);
    	user.setSumFee(user.getSumFee()-book.getPrice());
    		
        return OpResult.createOpResult(ReturnCodeEnum.E008);
    }
}

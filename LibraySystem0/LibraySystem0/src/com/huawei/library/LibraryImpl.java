package com.huawei.library;

import java.util.*;

import com.huawei.exam.BookStatusEnum;
import com.huawei.exam.ReturnCodeEnum;

/**
 * <p>Title: 待考生实现类</p>
 * 各方法按要求返回，程序库会组装报文输出
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
     * 定义全局变量
     *
    */
	
	Map<String, UserInfo> mapIdtoUser;
	Map<String, BookInfo> mapIdtoBook;
	String currentUserName;

    // 必须提供无参数构造函数，考生可在函数体中根据需要增加初始化代码
    // 程序库中会且只会生成一个LibraryImpl实例，并在整个进程生命周期中一直使用这个实例
    public LibraryImpl() {
    }

    /**
     * 考生需要实现的接口
     * initial命令接口，实现系统初始化功能
     * 程序库中已实现了文件的读取，读取后的结果当参数传入本函数
     *
     * @param users UserInfo[]：文件读取的用户信息
     * @param books BookInfo[]：文件读取的图书信息
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public OpResult opInit(UserInfo[] users, BookInfo[] books) {
    	mapIdtoUser=new HashMap<String, UserInfo>();
    	mapIdtoBook=new HashMap<String, BookInfo>();
    	for(int i=0;i<users.length;i++){
    		mapIdtoUser.put(users[i].getUserName(), users[i]);
    	}
    	for(int i=0;i<books.length;i++){
    		mapIdtoBook.put(books[i].getBookName(), books[i]);
    	}
    	currentUserName="0";
        return OpResult.createOpResult(ReturnCodeEnum.E000);
    }

    /**
     * 考生需要实现的接口
     * login命令接口，实现用户登录功能
     *
     * @param user String：用户名
     * @param password String：密码
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public OpResult opLogin(String userName, String password) {
    	
    	if(mapIdtoUser.containsKey(userName) && mapIdtoUser.get(userName).getPassword().equals(password)){
    		currentUserName=userName;
    		return OpResult.createOpResult(ReturnCodeEnum.E002);
    	}
    	else
    		return OpResult.createOpResult(ReturnCodeEnum.E001);
    	
    }

    /**
     * 考生需要实现的接口
     * query book命令接口，实现图书查询功能
     *
     * @param bookName String：图书名
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public OpResult opQueryBook(String bookName) {
    	
    	if(!mapIdtoBook.containsKey(bookName))
    		return OpResult.createOpResult(ReturnCodeEnum.E003);
    	
    	BookInfo book=mapIdtoBook.get(bookName);
    	if(book.getStatus()==BookStatusEnum.BOOK_IDLE){
    		//书籍处于空闲状态
    		book.setUserName("");
    		book.setBorrowDays(0);
    		return OpResult.createOpResult(book);
    	}
    	else if(book.getStatus()==BookStatusEnum.BOOK_BORROWED){
    		
    		BookInfo displayBook=new BookInfo(book.getBookName(),book.getPrice());
    		displayBook.setStatus(BookStatusEnum.BOOK_BORROWED);
    		displayBook.setUserName(book.getUserName());
    		displayBook.setBorrowDays(book.getBorrowDays());
    		displayBook.setTotalDays(9999);
    		return OpResult.createOpResult(displayBook);
    	}
        return OpResult.createOpResult(ReturnCodeEnum.E999);
    }

    /**
     * 考生需要实现的接口
     * query user命令接口，实现用户查询功能
     *
     * @param userName String：用户名
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public void sortBookName(UserInfo user){
    	if(user.getBookNum()<2)
    		return;
    	if(user.getBookNum()==2){
    		
    		String book1=user.getBooks()[0];
    		String book2=user.getBooks()[1];
    		if(book1.compareTo(book2)>0){
    			String sortBooks[] = new String[3];
    			sortBooks[0]=book2;
    			sortBooks[1]=book1;
    			user.setBooks(sortBooks);
    		}
    	}
    	if(user.getBookNum()==3){
    		
    		String book1=user.getBooks()[0];
    		String book2=user.getBooks()[1];
    		String book3=user.getBooks()[2];
    		if(book1.compareTo(book2)<=0){
    			
    			if(book1.compareTo(book3)<=0 && book2.compareTo(book3)<=0)
    				return;
    			else if(book1.compareTo(book3)<=0 && book2.compareTo(book3)>0){
        			String sortBooks[] = new String[3];
        			sortBooks[0]=book1;
        			sortBooks[1]=book3;
        			sortBooks[2]=book2;
        			user.setBooks(sortBooks);
        			return;
    			}
    			else{
        			String sortBooks[] = new String[3];
        			sortBooks[0]=book3;
        			sortBooks[1]=book1;
        			sortBooks[2]=book2;
        			user.setBooks(sortBooks);
        			return;
    			}
    		}
    		else{
    			if(book1.compareTo(book3)<=0 && book2.compareTo(book3)<=0){
        			String sortBooks[] = new String[3];
        			sortBooks[0]=book2;
        			sortBooks[1]=book1;
        			sortBooks[2]=book3;
        			user.setBooks(sortBooks);
        			return;
    			}
    			else if(book1.compareTo(book3)>0 && book2.compareTo(book3)<=0){
        			String sortBooks[] = new String[3];
        			sortBooks[0]=book2;
        			sortBooks[1]=book3;
        			sortBooks[2]=book1;
        			user.setBooks(sortBooks);
        			return;
    			}
    			else{
        			String sortBooks[] = new String[3];
        			sortBooks[0]=book3;
        			sortBooks[1]=book2;
        			sortBooks[2]=book1;
        			user.setBooks(sortBooks);
        			return;
    			}
    			
    		}
    }
}
    
    public OpResult opQueryUser(String userName) {
    	if(!mapIdtoUser.containsKey(userName))   //用户不存在
    		return OpResult.createOpResult(ReturnCodeEnum.E004);
    	
    	if(!userName.equals(currentUserName))  //用户查询的不是自己的状态
    		return OpResult.createOpResult(ReturnCodeEnum.E004);
    	
    	UserInfo user=mapIdtoUser.get(userName);
    	sortBookName(user);
    	return OpResult.createOpResult(user);
    }

    /**
     * 考生需要实现的接口
     * borrow命令接口，实现借书功能
     *
     * @param bookName String：图书名
     * @param days int：预借天数
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public OpResult opBorrowBook(String bookName, int days) {
    	
    	
    	if(!mapIdtoBook.containsKey(bookName))     //6.图书不存在
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	
    	//由于用户原因不能借书(1-4)
    	if(currentUserName.equals("0"))   //用户未登录
    		 return OpResult.createOpResult(ReturnCodeEnum.E005);
    	UserInfo user=mapIdtoUser.get(currentUserName);
    	BookInfo book=mapIdtoBook.get(bookName);
    	if(user.getBookNum()>=3 || user.getSumFee()+book.getPrice() >300 ||user.getCredit()<=user.getBookNum()){
    		//1.每个用户最多可以借3本，2.且借书的原价总额不能大于300元。
    		//3.信用额度大于已借图书数量时方可借书
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//计算租金
    	book.setRent(days);
    	int rent=book.getRent();
    	
  
    	if(user.getBalance()<rent)   //4.余额不足时不能借书
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	
    	//由于图书原因不能借书
    	if(book.getStatus()!=BookStatusEnum.BOOK_IDLE)  //5.图书不空闲
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	
    	//借书成功，图书处于借出状态
    	book.setStatus(BookStatusEnum.BOOK_BORROWED);
    	book.setUserName(user.getUserName());
    	book.setBorrowDays(days);
    	user.setBalance(user.getBalance()-rent);
    	user.setBookNum(user.getBookNum()+1);
    	String borrowedBooks[] = user.getBooks();
    	borrowedBooks[user.getBookNum()-1]=bookName;
    	user.setBooks(borrowedBooks);
    	user.setSumFee(user.getSumFee()+book.getPrice());
        return OpResult.createOpResult(ReturnCodeEnum.E006);
    }

    /**
     * 考生需要实现的接口
     * return命令接口，实现还书功能
     *
     * @param bookName String：图书名
     * @param days int：实借天数
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public OpResult opReturnBook(String bookName, int days) {
    	
    	
    	if(currentUserName.equals("0"))   //用户未登录,不能还书
   		 	return OpResult.createOpResult(ReturnCodeEnum.E005);
    	if(!mapIdtoBook.containsKey(bookName))     //图书不存在
    		return OpResult.createOpResult(ReturnCodeEnum.E007);
    	BookInfo book=mapIdtoBook.get(bookName);
    	if(book.getStatus()!=BookStatusEnum.BOOK_BORROWED)  //图书不在借出状态
    		return OpResult.createOpResult(ReturnCodeEnum.E007);
    	
    	book.setTotalDays(book.getTotalDays()+days);
    	book.setStatus(BookStatusEnum.BOOK_IDLE);
    	if(book.getTotalDays()>=300)            //书籍是否报废
    		book.setStatus(BookStatusEnum.BOOK_DISUSE);
    	
    	
    	UserInfo user=mapIdtoUser.get(book.getUserName());
    	if(days>book.getBorrowDays()){   //实际借书天数大于预借天数,信用-1
    		user.setCredit(user.getCredit()-1);
    	}
    	else if(days<book.getBorrowDays()){  //实际借书天数小于预借天数,返回部分rent
    		book.setActualRent(days);
    		int actualRent=book.getActualRent();
    		int rent=book.getRent();
    		user.setBalance(user.getBalance()+rent-actualRent);
    	}
    	book.setBorrowDays(0);
    	book.setUserName("");
    	
    	//书籍往前面全部移动一位
    	String books[]=user.getBooks();
    	int deletePosition=0;
    	while(!bookName.equals(books[deletePosition]))
    		deletePosition++;
    	int i;
    	for(i=deletePosition;i<user.getBookNum()-1;i++)
    		books[deletePosition]=books[deletePosition]+1;
    	
    	books[i]=null;
    	user.setBooks(books);
    	user.setBookNum(user.getBookNum()-1);
    	user.setSumFee(user.getSumFee()-book.getPrice());
    		
        return OpResult.createOpResult(ReturnCodeEnum.E008);
    }
}

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
	
	Map<String, UserInfo> userMap;
	Map<String, BookInfo> bookMap;
	UserInfo currentUser;

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
    	userMap=new HashMap<String, UserInfo>();
    	bookMap=new HashMap<String, BookInfo>();
    	//将用户存入内存
    	for(int i=0;i<users.length;i++)
    	{
    		userMap.put(users[i].getUserName(), users[i]);
    	}
    	//将书本存入内存
    	for(int i=0;i<books.length;i++)
    	{
    		bookMap.put(books[i].getBookName(), books[i]);
    	}
    	currentUser = null;
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
    	
    	if(userMap.containsKey(userName) && userMap.get(userName).getPassword().equals(password))
    	{
    		currentUser=userMap.get(userName);
    		return OpResult.createOpResult(ReturnCodeEnum.E002);
    	}
    	else{
    		return OpResult.createOpResult(ReturnCodeEnum.E001);
    	}
    }

    /**
     * 考生需要实现的接口
     * query book命令接口，实现图书查询功能
     *
     * @param bookName String：图书名
     *
     * @return OpResult：处理结果，通过OpResult的三个createOpResult方法生成需要的OpResult对象
     */
    public OpResult opQueryBook(String bookName)
    {
    	
    	if(!bookMap.containsKey(bookName))
    	{	//E003:查询图书失败
    		return OpResult.createOpResult(ReturnCodeEnum.E003);
    	}
    	BookInfo book = bookMap.get(bookName);
    	if(book.getStatus() == BookStatusEnum.BOOK_IDLE){
    		//书籍处于空闲状态
    		book.setUserName("");
    		book.setBorrowDays(0);
    		return OpResult.createOpResult(book);
    	}
    	if(book.getStatus() == BookStatusEnum.BOOK_BORROWED)
    	{
    		//用于显示的book属性
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
    	{ //用户不存在
    		return OpResult.createOpResult(ReturnCodeEnum.E004);
    	}
    	//管理员特权
    	
    	if(currentUser.getUserName().equals("admin")){
    		UserInfo user = userMap.get(userName);
    		Arrays.sort(user.getBooks());
        	//sortBookName(user);
        	return OpResult.createOpResult(user);
    	}
    	
    	if(!userName.equals(currentUser.getUserName()))
    	{  //用户查询的不是自己的状态
    		return OpResult.createOpResult(ReturnCodeEnum.E004);
    	}
    	//UserInfo user=userMap.get(userName);
    	Arrays.sort(currentUser.getBooks());
    	//sortBookName(user);
    	return OpResult.createOpResult(currentUser);
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
    	
    	
    	if(!bookMap.containsKey(bookName))
    	{//6.图书不存在
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//由于用户原因不能借书(1-4)
    	if(currentUser==null)
    	{//用户未登录
    		 return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//UserInfo user=currentUser;
    	BookInfo book=bookMap.get(bookName);
    	if(currentUser.getBookNum()>=3 ||
    			this.calculateSumFee(currentUser)+book.getPrice() >300 ||
    			currentUser.getCredit()<=currentUser.getBookNum())
    	{
    		//1.每个用户最多可以借3本，2.且借书的原价总额不能大于300元。
    		//3.信用额度大于已借图书数量时方可借书
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//计算租金
    	int rent  = this.calculateRent(days, book);
    	//book.setRent(days);
    	//int rent=book.getRent();
    	if(currentUser.getBalance()<rent) 
    	{//4.余额不足时不能借书
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//由于图书原因不能借书
    	if(book.getStatus()!=BookStatusEnum.BOOK_IDLE)
    	{//5.图书不空闲
    		return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	//借书成功，图书处于借出状态
    	book.setStatus(BookStatusEnum.BOOK_BORROWED);
    	book.setUserName(currentUser.getUserName());
    	book.setBorrowDays(days);
    	currentUser.setBalance(currentUser.getBalance()-rent);
    	currentUser.setBookNum(currentUser.getBookNum()+1);
    	String borrowedBooks[] = currentUser.getBooks();
    	borrowedBooks[currentUser.getBookNum()-1]=bookName;
    	currentUser.setBooks(borrowedBooks);
        return OpResult.createOpResult(ReturnCodeEnum.E006);
    }
    
    public int calculateSumFee(UserInfo userInfo)
    { 
    	int sumFee = 0;
    	String[] books = userInfo.getBooks();
    	for (String bookName : books) {
			if(bookMap.containsKey(bookName)){
				sumFee+=bookMap.get(bookName).getPrice();
			}
		}
    	return sumFee;
    }
    
    
    public int calculateRent(int days,BookInfo book)
    { 
    	int rent = 0;
    	//书的价格大于100
    	if(book.getPrice()>=100)
    	{   //
    		if(book.getTotalDays()+days <=90)
    		{
    			rent=5*days;
    		}
    		else if(book.getTotalDays()<90)
    		{
    			rent=(90-book.getTotalDays())*5+(days-90+book.getTotalDays())*3;
    		}
    		else
    		{
    			rent=days*3;
    		}
    	}
    	//书的价格小于100,大于50
    	else if(book.getPrice()>=50)
    	{
    		if(book.getTotalDays()+days <=90)
    		{
    			rent=3*days;
    		}
    		else if(book.getTotalDays()<90)
    		{
    			rent=(90-book.getTotalDays())*3+(days-90+book.getTotalDays())*2;
    		}
    		else
    		{
    			rent=days*2;
    		}
    	}
    	//书的价格小于50
    	else
    	{
    		rent=days;
    	}
    	return rent;
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
    	
    	
    	if(currentUser==null) 
    	{  //用户未登录,不能还书
   		 	return OpResult.createOpResult(ReturnCodeEnum.E005);
    	}
    	if(!bookMap.containsKey(bookName)) 
    	{//图书不存在
    		return OpResult.createOpResult(ReturnCodeEnum.E007);
    	}
    	BookInfo book = bookMap.get(bookName);
    	if(book.getStatus()!=BookStatusEnum.BOOK_BORROWED)
    	{//图书不在借出状态
    		return OpResult.createOpResult(ReturnCodeEnum.E007);
    	}
    	book.setTotalDays(book.getTotalDays()+days);
    	book.setStatus(BookStatusEnum.BOOK_IDLE);
    	if(book.getTotalDays()>=300) 
    	{//书籍是否报废
    		book.setStatus(BookStatusEnum.BOOK_DISUSE);
    	}
    	
    	UserInfo user=userMap.get(book.getUserName());
    	if(days>book.getBorrowDays())
    	{   //实际借书天数大于预借天数,信用-1
    		user.setCredit(user.getCredit()-1);
    	}
    	else if(days<book.getBorrowDays())
    	{  //实际借书天数小于预借天数,返回部分rent
    		int actualRent = this.calculateRent(days, book);
    		int rent  = this.calculateRent(book.getBorrowDays(), book);
    		user.setBalance(user.getBalance()+rent-actualRent);
    	}
    	book.setBorrowDays(0);
    	book.setUserName("");
    	
    	
    	
    	//删除归还的书籍
    	String [] books = user.getBooks();  	
    	String [] newbooks = new String[3];
    	for (int i = 0,j = 0;i < books.length;i++) {
			if(bookName != books[i]){
				newbooks[j] = books[i];
				j++;
			}
			
		}   	
    	user.setBooks(newbooks);
    	user.setBookNum(user.getBookNum() - 1);

    		
        return OpResult.createOpResult(ReturnCodeEnum.E008);
    }
    
}

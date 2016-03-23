/*
 * Copyright Notice:
 *      Copyright  1998-2009, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.huawei.library.ut;

import static org.junit.Assert.*;

import org.junit.*;

import com.huawei.exam.BookStatusEnum;
import com.huawei.exam.ReturnCodeEnum;
import com.huawei.library.*;

public class LibraryImplUT
{
    LibraryImpl libraryImpl = null;
    
    @Before
    public void setUp()
        throws Exception
    {
        libraryImpl = new LibraryImpl();
    }
    
    @After
    public void tearDown()
        throws Exception
    {
        libraryImpl = null;
    }
    
    @Test
    public void testOpInit()
    {
        //TODO:
    	UserInfo[] users=new UserInfo[3];
    	BookInfo[] books=new BookInfo[3];
    	
    	for (int i = 0; i < users.length; i++) {
			users[i]=new UserInfo("admin","admin", 0);
			users[i]=new UserInfo("user1","xxxxxx", 300);
			users[i]=new UserInfo("user2","yyyyyy", 200);
		}
    	
    	for (int i = 0; i < books.length; i++) {
    		books[i]=new BookInfo("bookar1", 100);
    		books[i]=new BookInfo("bookaq2", 60);
    		books[i]=new BookInfo("bookbp1",50);
		}
    	
    	OpResult actual = libraryImpl.opInit(users,books);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E000);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    //测试登录失败，用户名不存在
    public void testOpLogin1()
    {
    	UserInfo[] users=new UserInfo[3];
    	BookInfo[] books=new BookInfo[3];
    	
    	users[0]=new UserInfo("admin","admin", 0);
		users[1]=new UserInfo("user1","xxxxxx", 300);
		users[2]=new UserInfo("user2","yyyyyy", 200);
	
	
	
		books[0]=new BookInfo("bookar1", 100);
		books[1]=new BookInfo("bookaq2", 60);
		books[2]=new BookInfo("bookbp1",50);
    	
    	libraryImpl.opInit(users, books);
    	OpResult actual = libraryImpl.opLogin("yq", "123");        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E001);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    @Test
    //测试登录成功
    public void testOpLogin2()
    {
    	UserInfo[] users=new UserInfo[3];
    	BookInfo[] books=new BookInfo[3];
    	
    	
			users[0]=new UserInfo("admin","admin", 0);
			users[1]=new UserInfo("user1","xxxxxx", 300);
			users[2]=new UserInfo("user2","yyyyyy", 200);
		
    	
    	
    		books[0]=new BookInfo("bookar1", 100);
    		books[1]=new BookInfo("bookaq2", 60);
    		books[2]=new BookInfo("bookbp1",50);

    
    	libraryImpl.opInit(users, books);
    	OpResult actual = libraryImpl.opLogin("user1", "xxxxxx");        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E002);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    //查询书
    public void testOpQueryBook()
    {
    	UserInfo[] users=new UserInfo[3];
    	BookInfo[] books=new BookInfo[3];
    	
    	users[0]=new UserInfo("admin","admin", 0);
		users[1]=new UserInfo("user1","xxxxxx", 300);
		users[2]=new UserInfo("user2","yyyyyy", 200);
	
	
	
		books[0]=new BookInfo("bookar1", 100);
		books[1]=new BookInfo("bookaq2", 60);
		books[2]=new BookInfo("bookbp1",50);
		
		books[0].setStatus(BookStatusEnum.BOOK_BORROWED);
		books[0].setUserName("user1");
		books[0].setBorrowDays(20);
		books[0].setTotalDays(9999);
		
		books[1].setStatus(BookStatusEnum.BOOK_IDLE);
		books[1].setUserName("");
		books[1].setBorrowDays(0);
		books[1].setTotalDays(0);
		
		books[2].setStatus(BookStatusEnum.BOOK_DISUSE);
		books[2].setUserName("");
		books[2].setBorrowDays(0);
		books[2].setTotalDays(0);
		
    	
    	libraryImpl.opInit(users, books);
    	libraryImpl.opLogin("user1","xxxxxx");
    	//查询失败   图书不存在
    	OpResult actual = libraryImpl.opQueryBook("bookar");   
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E003);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals("", expected.toString(), actual.toString());
    	//查询借出成功
    	libraryImpl.opBorrowBook("bookar1", 20);
    	OpResult actual1 = libraryImpl.opQueryBook("bookar1");   
  
    	OpResult expected1 = OpResult.createOpResult(books[0]);
    	Assert.assertNotNull(actual1);
    	Assert.assertEquals("", expected1.toString(), actual1.toString());
    	//查询图书空闲状态
    	OpResult actual2 = libraryImpl.opQueryBook("bookaq2");   
    	OpResult expected2 = OpResult.createOpResult(books[1]);
    	Assert.assertNotNull(actual2);
    	Assert.assertEquals("", expected2.toString(), actual2.toString());
    	//查询图书报废状态
    	OpResult actual3 = libraryImpl.opQueryBook("bookbp1");   
    	OpResult expected3 = OpResult.createOpResult(books[2]);
    	Assert.assertNotNull(actual3);
    	Assert.assertEquals("", expected3.toString(), actual3.toString());
    }
    
    //用户查询
    @Test
    public void testOpQueryUser()
    {
    	UserInfo[] users=new UserInfo[3];
    	BookInfo[] books=new BookInfo[3];
    	
    	users[0]=new UserInfo("admin","admin", 0);
		users[1]=new UserInfo("user1","xxxxxx", 300);
		users[2]=new UserInfo("user2","yyyyyy", 200);
	
	
	
		books[0]=new BookInfo("bookar1", 100);
		books[1]=new BookInfo("bookaq2", 60);
		books[2]=new BookInfo("bookbp1",50);
		
		libraryImpl.opInit(users, books);
    	libraryImpl.opLogin("user1","xxxxxx");
    	//查询失败   用户不存在
    	OpResult actual = libraryImpl.opQueryUser("yq");   
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E004);
    	Assert.assertNotNull(actual);
    	Assert.assertEquals("", expected.toString(), actual.toString());
    	
    	//查询失败   用户查询的不是自己的状态
    	OpResult actual1 = libraryImpl.opQueryUser("user2");   
    	OpResult expected1 = OpResult.createOpResult(ReturnCodeEnum.E004);
    	Assert.assertNotNull(actual1);
    	Assert.assertEquals("", expected1.toString(), actual1.toString());
    	
    }
    
    @Test
    public void testOpBorrowBook()
    {
        //TODO:
        //fail("Not yet implemented");
    }
    
    @Test
    public void testOpReturnBook()
    {
        //TODO:
       //fail("Not yet implemented");
    }
}

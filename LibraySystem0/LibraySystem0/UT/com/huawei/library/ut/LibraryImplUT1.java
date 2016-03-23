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

import com.huawei.exam.ReturnCodeEnum;
import com.huawei.library.*;

public class LibraryImplUT1
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
    public void init(){
    	UserInfo[] users=new UserInfo[3];
    	BookInfo[] books=new BookInfo[8];
    	users[0]=new UserInfo("user1","xxxxxx",300);
    	users[1]=new UserInfo("user2","yyyyyy",200);
    	users[2]=new UserInfo("user3","zzzzzz",1000);
    	books[0]=new BookInfo("bookar1",100);
    	books[1]=new BookInfo("bookaq2",60);
    	books[2]=new BookInfo("bookbp1",50);
    	books[3]=new BookInfo("bookbo2",140);
    	books[4]=new BookInfo("bookcn1",30);
    	books[5]=new BookInfo("bookcm2",40);
    	books[6]=new BookInfo("bookdl1",100);
    	books[7]=new BookInfo("bookdk2",200);
    	libraryImpl.opInit(users,books);   
    }
    @Test
    public void testOpInit()
    {
        //TODO:
    	UserInfo[] users=new UserInfo[2];
    	BookInfo[] books=new BookInfo[2];
    	users[0]=new UserInfo("user1","xxxxxx",300);
    	users[1]=new UserInfo("user2","yyyyyy",200);
    	books[0]=new BookInfo("bookar1",100);
    	books[1]=new BookInfo("bookaq2",60);
    	OpResult actual = libraryImpl.opInit(users,books);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E000);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    


	@Test
    public void testOpLogin()
    {
        //TODO:
		init();
    	
    	OpResult actual = libraryImpl.opLogin("user1","xxxxxx");        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E002);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());

    	actual = libraryImpl.opLogin("user1","xxxxxy");        
    	expected = OpResult.createOpResult(ReturnCodeEnum.E001);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());

    }
    
    @Test
    public void testOpQueryBook()
    {
        //TODO:
        //fail("Not yet implemented");
    }
    
    @Test
    public void testOpQueryUser10()
    {
        //TODO:
    	init();
    	libraryImpl.opLogin("user1","xxxxxx");
    	libraryImpl.opLogin("user2","yyyyyy");
    	
    	OpResult actual = libraryImpl.opQueryUser("user1");        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E004);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());

    }
    
    @Test
    public void testOpBorrowBook4()
    {
        //TODO:
    	init();
    	libraryImpl.opLogin("user1","xxxxxx");
    	OpResult actual = libraryImpl.opBorrowBook("bookar1",20);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E006);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpBorrowBook6()
    {
        //TODO:
    	init();
    	libraryImpl.opLogin("user1","xxxxxx");
    	libraryImpl.opBorrowBook("bookar1",20); 
    	OpResult actual = libraryImpl.opBorrowBook("bookar1",20);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E005);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpBorrowBook7()
    {
        //TODO:
    	init();
    	libraryImpl.opLogin("user2","yyyyyy");
    	
    	OpResult actual = libraryImpl.opBorrowBook("bookar1",80);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E005);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    @Test
    public void testOpBorrowBook8()
    {
        //TODO:
    	init();
    	libraryImpl.opLogin("user1","xxxxxx");
    	libraryImpl.opBorrowBook("bookar1",20);
    	libraryImpl.opBorrowBook("bookaq2",10);
    	libraryImpl.opBorrowBook("bookbp1",10);
    	OpResult actual = libraryImpl.opBorrowBook("bookbo2",10);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E005);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    
    @Test
    public void testOpBorrowBook9()
    {
        //TODO:
    	init();
    	libraryImpl.opLogin("user1","xxxxxx");
    	libraryImpl.opBorrowBook("bookar1",1);
    	libraryImpl.opBorrowBook("bookbo2",1);
    	
    	OpResult actual = libraryImpl.opBorrowBook("bookdk2",1);        
    	OpResult expected = OpResult.createOpResult(ReturnCodeEnum.E005);
        Assert.assertNotNull(actual);
        Assert.assertEquals("", expected.toString(), actual.toString());
    }
    
    
    @Test
    public void testOpReturnBook()
    {
        //TODO:
        //fail("Not yet implemented");
    }
}

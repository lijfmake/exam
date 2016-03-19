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
        fail("Not yet implemented");
    }
    
    @Test
    public void testOpLogin()
    {
        //TODO:
        fail("Not yet implemented");
    }
    
    @Test
    public void testOpQueryBook()
    {
        //TODO:
        fail("Not yet implemented");
    }
    
    @Test
    public void testOpQueryUser()
    {
        //TODO:
        fail("Not yet implemented");
    }
    
    @Test
    public void testOpBorrowBook()
    {
        //TODO:
        fail("Not yet implemented");
    }
    
    @Test
    public void testOpReturnBook()
    {
        //TODO:
        fail("Not yet implemented");
    }
}

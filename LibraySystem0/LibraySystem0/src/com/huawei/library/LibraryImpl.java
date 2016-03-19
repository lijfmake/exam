package com.huawei.library;

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
        return OpResult.createOpResult(ReturnCodeEnum.E002);
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
    public OpResult opQueryUser(String userName) {
        return OpResult.createOpResult(ReturnCodeEnum.E999);
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
        return OpResult.createOpResult(ReturnCodeEnum.E999);
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
        return OpResult.createOpResult(ReturnCodeEnum.E999);
    }
}

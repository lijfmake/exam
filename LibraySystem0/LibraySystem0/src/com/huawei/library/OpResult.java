package com.huawei.library;

import com.huawei.exam.ReturnCodeEnum;
import com.huawei.exam.ReturnCodeRst;

/**
 * <p>Title: 本类供考生调用，不允许更改</p>
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
public abstract class OpResult {

    /**
     * 创建结果，给客户端返回简单结果信息
     * 如：
     * E000:初始化成功
     * E010:挂失成功
     *
     * @param errCode ReturnCodeEnum：返回码枚举
     *
     * @return OpResult：返回结果对象
     */
    public static OpResult createOpResult(ReturnCodeEnum errCode) {
        return new ReturnCodeRst(errCode);
    }

    /**
     * 创建结果，给客户端返回用户查询的结果信息
     *
     * @param user UserInfo：用户信息结构
     *
     * @return OpResult：返回结果对象
     */
    public static OpResult createOpResult(UserInfo user) {
        return new ReturnCodeRst(user);
    }

    /**
     * 创建结果，给客户端返回图书查询的结果信息
     *
     * @param info TaskInfo：图书信息结构
     *
     * @return OpResult：返回结果对象
     */
    public static OpResult createOpResult(BookInfo book) {
        return new ReturnCodeRst(book);
    }

    public abstract String toString();
}

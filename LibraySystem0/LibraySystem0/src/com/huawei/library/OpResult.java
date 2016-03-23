package com.huawei.library;

import com.huawei.exam.ReturnCodeEnum;
import com.huawei.exam.ReturnCodeRst;

/**
 * <p>Title: ���๩�������ã����������</p>
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
     * ������������ͻ��˷��ؼ򵥽����Ϣ
     * �磺
     * E000:��ʼ���ɹ�
     * E010:��ʧ�ɹ�
     *
     * @param errCode ReturnCodeEnum��������ö��
     *
     * @return OpResult�����ؽ������
     */
    public static OpResult createOpResult(ReturnCodeEnum errCode) {
        return new ReturnCodeRst(errCode);
    }

    /**
     * ������������ͻ��˷����û���ѯ�Ľ����Ϣ
     *
     * @param user UserInfo���û���Ϣ�ṹ
     *
     * @return OpResult�����ؽ������
     */
    public static OpResult createOpResult(UserInfo user) {
        return new ReturnCodeRst(user);
    }

    /**
     * ������������ͻ��˷���ͼ���ѯ�Ľ����Ϣ
     *
     * @param info TaskInfo��ͼ����Ϣ�ṹ
     *
     * @return OpResult�����ؽ������
     */
    public static OpResult createOpResult(BookInfo book) {
        return new ReturnCodeRst(book);
    }

    public abstract String toString();
}

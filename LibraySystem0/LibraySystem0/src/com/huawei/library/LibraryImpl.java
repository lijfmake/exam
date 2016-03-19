package com.huawei.library;

import com.huawei.exam.ReturnCodeEnum;

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
        return OpResult.createOpResult(ReturnCodeEnum.E002);
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
        return OpResult.createOpResult(ReturnCodeEnum.E999);
    }

    /**
     * ������Ҫʵ�ֵĽӿ�
     * query user����ӿڣ�ʵ���û���ѯ����
     *
     * @param userName String���û���
     *
     * @return OpResult����������ͨ��OpResult������createOpResult����������Ҫ��OpResult����
     */
    public OpResult opQueryUser(String userName) {
        return OpResult.createOpResult(ReturnCodeEnum.E999);
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
        return OpResult.createOpResult(ReturnCodeEnum.E999);
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
        return OpResult.createOpResult(ReturnCodeEnum.E999);
    }
}

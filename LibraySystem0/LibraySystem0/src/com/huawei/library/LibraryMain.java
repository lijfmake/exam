package com.huawei.library;

import com.huawei.exam.Command;
import com.huawei.exam.ExamCommand;
import com.huawei.exam.ExamServer;

/**
 *
 * <p>Title: ��ִ����</p>
 * ���������޸ģ��������ע���ļ�����
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
public class LibraryMain
{
    public LibraryMain()
    {
    }

    public static void main(String[] args)
    {
        /**
         * ����Socket��������5555�˿ڣ���Socket��ȡ����ᶪ��Command���command����ִ��
         * Command���command�����Ѿ�ʵ���˴�Socket���յ��ַ�����Ľ�����ַ�
         * ����ֻ��Ҫʵ��LibraryImpl��ĸ�����ӿڼ��ɡ�
         */
        Command cmd = new ExamCommand();
        ExamServer examServer = new ExamServer(cmd);
        examServer.run(args);
    }
}

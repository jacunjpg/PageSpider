package cn.uitl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContentUtil {

	public static void writeFileData(String words){
        FileWriter fileData = null;
        try {
            // ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
            File file = new File("d:/data/sinaContent.txt");
            fileData = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fileData);
        pw.println(words);
        pw.flush();
        pw.close();
        fileData=null;
    }
}

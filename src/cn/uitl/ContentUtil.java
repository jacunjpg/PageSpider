package cn.uitl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContentUtil {

	public static void writeFileData(String words){
        FileWriter fileData = null;
        try {
            // 如果文件存在，则追加内容；如果文件不存在，则创建文件
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

package pku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class fileReader {
    // 阅读文件，需要修改读取的文件名
    public static void main(String[] args) throws Exception {
        System.out.print(readFile("test.txt"));
    }

    //阅读文件的函数，需要提供文件名作为参数
    public static String readFile(String filename) throws Exception {
        StringBuilder result =new StringBuilder();
        File file = new File("/Users/chenguoqiang/IdeaProjects/JavaMQ/src/main/java/ChenGQ/data/"+filename);
            BufferedReader br =new BufferedReader(new FileReader(file));
            String s ;
            while((s =br.readLine()) != null) { //一次读一行内容
                result.append(System.lineSeparator() +s);
            }
            br.close();
        return result.toString();
    }

}

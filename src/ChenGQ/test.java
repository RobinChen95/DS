package ChenGQ;

import java.io.*;

public class test {
    // 测试分支
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/chenguoqiang/IdeaProjects/JavaMQ/src/main/java/ChenGQ/data/test.txt");
        System.out.println(readFile(file));
    }

    public static String readFile(File file) throws Exception {
        StringBuilder result =new StringBuilder();
        try {
            BufferedReader br =new BufferedReader(new FileReader(file));
            String s =null;
            while((s =br.readLine()) != null) { //一次读一行内容
                result.append(System.lineSeparator() +s);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}

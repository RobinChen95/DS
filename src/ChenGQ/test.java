package ChenGQ;

import java.io.*;
import java.util.HashMap;

public class test {
    // 测试分支
    public static void main(String[] args) {
        final HashMap<Integer,String> hm = new HashMap<>();
        //这句话会报错 hm = new HashMap<>();
        hm.put(1,"第1次");
        hm.put(2,"第2次");
        hm.put(3,"第3次");

        hm.remove(1);
        System.out.println(hm.toString());
    }


}

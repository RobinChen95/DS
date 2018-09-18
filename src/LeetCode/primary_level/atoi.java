package LeetCode.primary_level;

import java.util.ArrayList;
import java.util.Arrays;

public class atoi {
    public static void main(String args[]){
        String s = "";
        atoi test = new atoi();
        System.out.println(test.myAtoi(s));
    }

    public int myAtoi(String str) {
        ArrayList<Character> al = new ArrayList<>();
        str.trim();
        if (str==""||(str.charAt(0)<=0x30&str.charAt(0)>=0x39))return 0;
        long result=0;
        boolean minus = false;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)=='-'){minus=true;}
            else if ((str.charAt(i)>=0x30&str.charAt(i)<=0x39))
                al.add(str.charAt(i));
        }
        int[] ch = new int[al.size()];
        for (int i=0;i<al.size();i++){
            ch[i]=al.get(i)-0x30;
        }
        for (int i=0;i<ch.length;i++){
            result+=ch[i]*Math.pow(10,ch.length-1-i);
        }
        if (minus){
            if (result>2147483648l)
                result = -2147483648;
            else result*=-1;
        }
        else{
            if (result>2147483647l)
                result = 2147483647;
        }
        return (int)result;
    }
}

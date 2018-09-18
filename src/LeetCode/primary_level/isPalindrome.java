package LeetCode.primary_level;

import java.util.ArrayList;
import java.util.Arrays;

public class isPalindrome {
    public static void main(String args[]){
        String test = "A man, a plan, a canal: Panama";
        isPalindrome pd = new isPalindrome();
        System.out.println(pd.isPalindrome(test));
    }

    public boolean isPalindrome(String s) {
        boolean ok = true;

        ArrayList<Character> al = new ArrayList<>();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)>=0x30&&s.charAt(i)<=0x39)
                al.add(s.charAt(i)); //判断是否为数字
            if (s.charAt(i)>=0x41&&s.charAt(i)<=0x5a)
                al.add(s.charAt(i)); //判断是否为大写字母
            if (s.charAt(i)>=0x61&&s.charAt(i)<=0x7a)
                al.add((char)(s.charAt(i)-0x20)); //判断是否为小写字母
        }

        char[] ch = new char[al.size()];

        for (int i=0;i<al.size();i++){
            ch[i]=al.get(i);
        }

        for (int i=0;i<ch.length/2;i++){
            if (ch[i]-ch[ch.length-1-i]!=0){
                ok = false;
                break;
            }
        }
        return ok;
    }
}


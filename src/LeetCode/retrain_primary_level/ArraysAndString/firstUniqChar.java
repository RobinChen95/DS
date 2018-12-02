package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.HashMap;

/*给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.


注意事项：您可以假定该字符串只包含小写字母。*/

public class firstUniqChar {
    public int firstUniqChar1(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hm.containsKey(s.charAt(i))){
                //如果存在，则取出并加1
                hm.replace(s.charAt(i),hm.get(s.charAt(i))+1);
            }
            //否则，建立键值对，并赋值1
            else hm.put(s.charAt(i),1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (hm.get(s.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }

    //leetcode上的最佳解法
    //使用String的indexof()与lastindexof()方法
    public int firstUniqChar(String s) {
        int res = -1;
        int idx;
        for (char c = 'a'; c <= 'z'; c++) {
            idx = s.indexOf(c);
            if (idx != -1 && idx == s.lastIndexOf(c))
                res = (res == -1) ? idx : Math.min(res, idx);
        }
        return res;
    }

    public static void main(String[] args) {
        firstUniqChar fuc = new firstUniqChar();
        String s = new String("abcdefgabcde");
        System.out.println(fuc.firstUniqChar(s));
    }
}

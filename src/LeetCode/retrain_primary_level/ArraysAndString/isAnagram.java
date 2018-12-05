package LeetCode.retrain_primary_level.ArraysAndString;

/*给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？*/

import java.util.Arrays;

public class isAnagram {

    public boolean isAnagram1(String s, String t) {
        char[] s_char = s.toCharArray();
        char[] t_char = t.toCharArray();
        Arrays.sort(s_char);
        Arrays.sort(t_char);
        if (Arrays.equals(s_char,t_char))return true;
        else return false;
    }

    //leetcode上面的最佳解法
    public boolean isAnagram(String s, String t) {
        int[] num = new int[200];
        if(s.length() != t.length()){
            return false;
        }
        for(int i = 0; i < s.length(); i++){
            num[s.charAt(i)]++;
        }
        for(int i = 0; i < t.length(); i++){
            num[t.charAt(i)]--;
        }
        for(int temp : num){
            if(temp != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        isAnagram ia = new isAnagram();
        String s1 = "natarim";
        String s2 = "tanarim";
        System.out.println(ia.isAnagram1(s1,s2));
    }
}

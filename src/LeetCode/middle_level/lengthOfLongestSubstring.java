package LeetCode.middle_level;

import java.util.HashSet;

public class lengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> hs = new HashSet<>();
            hs.add(s.charAt(i));
            int j = i+1;
            while(j<=s.length()){
                if (j<s.length()&&!hs.contains(s.charAt(j))){
                    hs.add(s.charAt(j));
                    j++;
                }
                else {
                    if (max<j-i){
                        max = j-i;
                    }
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring lls = new lengthOfLongestSubstring();
        String test = "";
        System.out.println(lls.lengthOfLongestSubstring(test));
    }
}

package LeetCode.primary_level.ArraysAndString;

import java.util.HashMap;

public class theSameWord {
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()) return false;
        HashMap<Character,Integer> map1 = new HashMap<Character, Integer>();
        HashMap<Character,Integer> map2 = new HashMap<Character, Integer>();
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        for (int i=0;i<s.length();i++){
            if (!map1.containsKey(ch1[i])){map1.put(ch1[i],1);}
            else map1.replace(ch1[i],map1.get(ch1[i])+1);
            if (!map2.containsKey(ch2[i])){map2.put(ch2[i],1);}
            else map2.replace(ch2[i],map2.get(ch2[i])+1);
        }
        if (map1.equals(map2))return true;
        else return false;
    }
}

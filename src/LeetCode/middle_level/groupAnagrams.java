package LeetCode.middle_level;

/*
* 这个其实是作弊，调用了Java自带的函数
* */

import java.util.*;

public class groupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> al = new ArrayList<>();
        HashMap<String, ArrayList<String>> hm = new HashMap<>();
        String[] new_strs = new String[strs.length];
        char[][] strs_array = new char[strs.length][];
        System.arraycopy(strs, 0, new_strs, 0, strs.length);
        for (int i = 0; i < strs.length; i++) {
            strs_array[i]=strs[i].toCharArray();
            Arrays.sort(strs_array[i]);
        }
        for (int i = 0; i < strs.length; i++) {
            if (hm.containsKey(String.valueOf(strs_array[i]))) {
                hm.get(String.valueOf(strs_array[i])).add(strs[i]);
            } else {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                hm.put(String.valueOf(strs_array[i]), temp);
            }
        }
        for (String key : hm.keySet()) {
            al.add(hm.get(key));
        }
        return al;
    }

    //leetcode上的解法，其实都一样
    public List<List<String>> groupAnagrams2(String[] strs) {

        Map<String,List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;++i){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key  = String.valueOf(chars);
            if(map.containsKey(key)){
                map.get(key).add(strs[i]);
            }else{
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(key,list);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams ga = new groupAnagrams();
        List<List<String>> res = ga.groupAnagrams(str);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j)+",");
            }
            System.out.println();
        }
    }
}

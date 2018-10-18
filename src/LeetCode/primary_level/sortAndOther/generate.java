package LeetCode.primary_level.sortAndOther;

import java.util.*;

public class generate {
    //帕斯卡三角形
    public List<List<Integer>> generate(int numRows) {
        //init
        List<List<Integer>> list = new ArrayList<>();
        if (numRows==0)return list;
        list.add(new ArrayList<>());
        list.get(0).add(1);
        if (numRows==1)return list;
        list.add(new ArrayList<>());
        list.get(1).add(1);
        list.get(1).add(1);
        //init

        for (int i = 2; i < numRows; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 2; i < numRows; i++) {
            for (int m = i; m >= 0; m--) {
                list.get(i).add(1);
            }
            for (int j = 1; j < list.get(i - 1).size(); j++) {
                list.get(i).set(j, list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        generate ge = new generate();
        System.out.println(Arrays.toString(ge.generate(3).toArray()));
    }
}

package com.pku.programmingTest2018;

import java.util.*;

public class q3_SubQueue {
    public static void main(String args[]){
        int[] array = {9,5,6,7,4,3,5,4,2};
        q3_SubQueue q3 = new q3_SubQueue();
        System.out.println(Arrays.toString(q3.subArray(array,array.length)));
    }

    public int[] subArray(int[] arr, int n){
        //使用Arrays.sort方法排序
        Arrays.sort(arr);
        //使用Hashset去重
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i=0;i<arr.length;i++){
            if (!hs.contains(arr[i])){hs.add(arr[i]);}
        }
        ArrayList<Integer> al = new ArrayList<>();
        int[] newArray = new int[hs.size()];
        for(int x:hs){al.add(x);}
        for (int i=0;i<al.size();i++){
            newArray[i]=al.get((al.size()-1-i));
        }
        return newArray;
    }

}

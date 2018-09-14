package LeetCode.primary_level;

import java.util.ArrayList;
import java.util.Arrays;

public class Insertion {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int m,n;m=n=0;
        while(m<nums1.length&&n<nums2.length) {
            if (nums1[m] == nums2[n]) {
                al.add(nums1[m]);
                m++;
                n++;
            }
            else if (nums1[m] < nums2[n]) { //*
                m++;
            }
            else {
                n++;
            }
        }
        int[] arr = new int[al.size()];
        for (int i=0;i<arr.length;i++){
            arr[i]=al.get(i);
        }
        return arr;
    }

    public static void main(String args[]){
        Insertion is = new Insertion();
        int[] array1 = {1,2,2,1};
        int[] array2 = {2,2};
        System.out.println(Arrays.toString(is.intersect(array1,array2)));
    }
}

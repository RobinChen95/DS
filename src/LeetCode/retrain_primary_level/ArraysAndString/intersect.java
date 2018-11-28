package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.*;

public class intersect {

    public int[] intersect1(int[] nums1, int[] nums2) {
        long startTime = System.nanoTime();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> al = new ArrayList<>();
        int i=0;
        int j=0;
        while (i<nums1.length&j<nums2.length){
            if (nums1[i]>nums2[j])j++;
            else if (nums1[i]<nums2[j])i++;
            else {
                al.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[al.size()];
        for (int k = 0; k < al.size(); k++) {
            result[k]=al.get(k);
        }
        long endTime = System.nanoTime();
        System.out.println("我的执行时间：");
        System.out.println(endTime-startTime+"ns");
        return result;
    }

    //进仔的解法
    public int[] intersect3(int[] nums1, int[] nums2) {
            long startTime = System.nanoTime();
            List<Integer> res = new ArrayList<Integer>();
            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(int i=0; i<nums1.length; i++){
                Integer value = map.get(nums1[i]);
                map.put(nums1[i], (value == null ? 0 : value) + 1);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (map.containsKey(nums2[i]) && map.get(nums2[i]) != 0) {
                    res.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i]) - 1);
                }
            }
            int[] ans = new int[res.size()];
            int i = 0;
            for(Integer e: res)
                ans[i++] = e;
            long endTime = System.nanoTime();
            System.out.println("进仔的执行时间：");
            System.out.println(endTime-startTime+"ns");
            return ans;
        }


    //leetcode上的最佳解法，此解法可能不是最佳
    public int[] intersect2(int[] nums1, int[] nums2) {
        long startTime = System.nanoTime();
        if(nums1.length == 0 || nums2.length == 0)   {
            return new int[0];
        }

        int[] ret1 = new int[Math.max(nums1.length, nums2.length)];
        int len1 = 0;
        boolean[] bl1 = new boolean[ret1.length];
        for (int i=0; i < nums2.length; i++) {
            int start = 0;
            while( (start = find(nums1, nums2[i], start)) != -1 ) {
                if(bl1[start] == false) {
                    ret1[len1++] = nums2[i];
                    bl1[start] = true;
                    break;
                }
                start++;
            }
        }

        int[] ret = new int[len1];
        for (int i=0; i<len1; i++) {
            ret[i] = ret1[i];
        }
        long endTime = System.nanoTime();
        System.out.println("leetcode的执行时间：");
        System.out.println(endTime-startTime+"ns");
        return ret;

    }

    public int find(int[] nums, int val, int i) {
        for (; i < nums.length; i++) {
            if(nums[i] == val) {
                return i;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        intersect is = new intersect();
        int[] a1 = {4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5};
        int[] b1 = {9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4};
        int[] a2 = {4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5};
        int[] b2 = {9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4};
        int[] a3 = {4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5,4,9,5};
        int[] b3 = {9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4,9,4,9,8,4};
        System.out.println(Arrays.toString(is.intersect1(a1,b1)));
        System.out.println(Arrays.toString(is.intersect2(a2,b2)));
        System.out.println(Arrays.toString(is.intersect3(a3,b3)));
    }
}

package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.Arrays;
import java.util.HashSet;

public class removeDuplicates {

    //用HashSet在leetcode中会判断，原因不明
    public int removeDuplicates1(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }
        //HashSet可以直接调用toString方法打印，而数组直接调用toString方法会打印其地址
        System.out.println(hashSet.toString());
        return hashSet.size();
    }

    //用一个end记录数组尾部，然后发现重复就从前往后覆盖一次，注意需要i--
    public int removeDuplicates2(int[] nums) {
        int end = nums.length;
        for (int i = 0; i < end - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                for (int j = i + 1; j < end - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                end--;
                i--;
            }
        }
        System.out.println(Arrays.toString(nums));
        return end;
    }

    //leetcode上最好的解法
    public int removeDuplicates3(int[] nums) {
        int number = 0;//标记计数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[number]) {
                number++;
                nums[number] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return number + 1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4};
        int[] b = {1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4};
        int[] c = {1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4};
        removeDuplicates rd = new removeDuplicates();
        System.out.println(rd.removeDuplicates1(a));
        System.out.println(rd.removeDuplicates2(b));
        System.out.println(rd.removeDuplicates3(c));
    }

}

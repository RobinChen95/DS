package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.HashSet;

public class containsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hs.add(nums[i]));
            else return true;
        }
        return false;
    }

    //leetcode上的最佳解答
    public boolean containsDuplicate2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    break;
                } else if (nums[i] == nums[j]) {
                    return true;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        containsDuplicate cd = new containsDuplicate();
        System.out.println(cd.containsDuplicate(a));
    }
}

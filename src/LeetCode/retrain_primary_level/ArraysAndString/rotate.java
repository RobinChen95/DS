package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.Arrays;

public class rotate {

    public void rotate(int[] nums, int k) {
        //右移
        for (int i = 0; i < k; i++) {
            rotate_one_num(nums);
        }
    }

    public void rotate_one_num(int[] nums) {
        int temp = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = temp;
    }

    //leetcode上的最佳解法
    public void rotate2(int[] nums, int k) {
        if (k > 0) {
            k = k % nums.length;

            int[] temp = new int[k];
            int len = nums.length;

            for (int i = 0; i < k; i++) {
                temp[i] = nums[i + len - k];
            }

            for (int i = len - k - 1; i >= 0; i--) {
                nums[i + k] = nums[i];
            }


            for (int i = 0; i < k; i++)
                nums[i] = temp[i];
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate ro = new rotate();
        ro.rotate(a, k);
        System.out.println(Arrays.toString(a));
    }
}

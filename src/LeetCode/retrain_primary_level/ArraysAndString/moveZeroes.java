package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.Arrays;

/*给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。*/

public class moveZeroes {
    public void moveZeroes1(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - count; i++) {
            if (nums[i] == 0) {
                for (int j = i; j < nums.length - 1 ; j++) {
                    nums[j] = nums[j + 1];
                }
                count++;
                //记得加上i--，否则会报错
                i--;
            }
        }
        while (count > 0) {
            nums[nums.length - count] = 0;
            count--;
        }
    }

    //leetcode上的最佳解法
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[flag++] = nums[i];
            }
        }
        for (int i = flag; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] a = {0,0,1};
        moveZeroes mz = new moveZeroes();
        mz.moveZeroes1(a);
        System.out.println(Arrays.toString(a));
    }
}

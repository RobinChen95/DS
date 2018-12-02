package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]*/

public class twoSum {
    public int[] twoSum1(int[] nums, int target) {
        int temp=0;
        int[] result = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsValue(target - nums[i])){
                result[0] = i;
                temp = nums[i];
                break;
            }
            hm.put(i, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (temp==target-nums[i]){
                result[1]=i;
                Arrays.sort(result);
                return result;
            }
        }
        return null;
    }

    //leetcode上的最佳解法
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (map.containsKey(target - x)) {
                return new int[] {map.get(target - x), i };
            }
            map.put(x, i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] a = {3,2,4};
        int target = 6;
        twoSum ts = new twoSum();
        System.out.println(Arrays.toString(ts.twoSum1(a, target)));
    }
}

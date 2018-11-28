package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

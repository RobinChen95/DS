package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.HashSet;

/*给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true*/

public class containsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hs.add(nums[i])) ;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {9,8,7,6,5,4,3,4,6};
        containsDuplicate cd = new containsDuplicate();
        //System.out.println(cd.containsDuplicate(a));
        System.out.println(cd.containsDuplicate(a));
    }
}

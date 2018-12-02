package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.Arrays;
import java.util.HashSet;

/*
给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:

给定数组 nums = [1,1,2],

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

你不需要考虑数组中超出新长度后面的元素。
示例 2:

给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。
说明:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
*/

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

package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.*;

/*给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？*/

public class intersect {

    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect1(nums2, nums1);
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int nums[] = new int[nums1.length];
        int len = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                nums[len++] = nums1[i];
                ++i;
                ++j;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int num[] = new int[len];
        System.arraycopy(nums, 0, num, 0, len);
        return num;
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

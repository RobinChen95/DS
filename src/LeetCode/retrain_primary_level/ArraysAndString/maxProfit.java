package LeetCode.retrain_primary_level.ArraysAndString;

public class maxProfit {
    //用贪心法解决
    public int maxProfit1(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i]<prices[i+1]){
                profit+=prices[i+1]-prices[i];
            }
        }
        return profit;
    }

    //leetcode上面的最佳解法
    public int maxProfit2(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int i = 0;
        int max = 0;
        while(i < nums.length){
            //找到附近最小的数
            while(i < nums.length - 1 && nums[i+1] <= nums[i])
                i++;
            int min = nums[i];
            //找到附近最大的数
            while(i < nums.length - 1 && nums[i+1] >= nums[i])
                i++;
            max += (i < nums.length) ? (nums[i++] - min) : 0;
        }
        return max;
    }
}

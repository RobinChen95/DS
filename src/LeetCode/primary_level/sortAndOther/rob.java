package LeetCode.primary_level.sortAndOther;

public class rob {
    public int rob(int[] nums) {

        int len = nums.length;
        if (len <= 0)
            return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < len; ++i) {
            dp[i + 1] = Math.max(dp[i], nums[i] + dp[i - 1]);
        }
        return dp[len];

    }

    public static void main(String[] args) {
        rob r = new rob();
        int[] a = {2, 7, 9, 3, 1};
        System.out.println(r.rob(a));
    }
}

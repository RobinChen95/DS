package LeetCode.primary_level.sortAndOther;

public class missingNumber {
    public int missingNumber(int[] nums) {
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
        }
        return sumofArray(nums.length)-sum;
    }

    public int sumofArray(int n){
        return (n+1)*n/2;
    }
}

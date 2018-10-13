package LeetCode.primary_level.sortAndOther;

public class maxSubArray {

    public int maxSubArray(int[] nums) {
        if (nums.length==0) return 0;
        if (nums.length==1) return nums[0];
        int cur = 0;
        int sum = Integer.MIN_VALUE;
        for (int i=0;i<nums.length;i++){
            if (cur<0)cur=nums[i];
            else cur+=nums[i];
            if (cur>sum) sum = cur;
        }
        return sum;
    }

    public static void main(String args[]){
        int[] a = {1,2};
        maxSubArray ms = new maxSubArray();
        System.out.println(ms.maxSubArray(a));
    }
}

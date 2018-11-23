package LeetCode.retrain_primary_level.ArraysAndString;

public class singleNumber {

    //注意，1^2=3
    //此解答即为最佳答案
    public int singleNumber(int[] nums) {
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp^=nums[i];
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] a = {1,2,2,3,3};
        singleNumber sn = new singleNumber();
        System.out.println(sn.singleNumber(a));
    }
}

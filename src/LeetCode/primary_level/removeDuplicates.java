package LeetCode.primary_level;

public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++){
            int temp=nums[i];
            for (int j=i+1;j<n;j++) {
                if (temp == nums[j]) {
                    for (int k=j;k<n-1;k++) {
                        nums[j] = nums[k+1];
                        //System.out.println("nums[j]:"+nums[j]);
                        //System.out.println("nums[k+1]:"+nums[k+1]);
                    }
                    //System.out.println("here");
                    n--;
                }
            }
        }
        return n;
    }

    public static void main(String args[]){
        int[] test = {1,1,2};
        removeDuplicates rd = new removeDuplicates();
        System.out.println(rd.removeDuplicates(test));
    }
}

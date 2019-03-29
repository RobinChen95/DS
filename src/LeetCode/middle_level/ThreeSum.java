package LeetCode.middle_level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list=new ArrayList();
        for(int i=0;i<nums.length-2;i++){
            if(i!=0&&(nums[i]==nums[i-1]))continue;
            int j=i+1;
            int k=nums.length-1;
            while(j<k){
                int sum=nums[j]+nums[k]+nums[i];
                if(sum<0){
                    j++;
                }
                else if(sum>0){
                    k--;
                }
                else{
                    while(j<k&&nums[j]==nums[j+1])j++;
                    while(j<k&&nums[k]==nums[k-1])k--;
                    List<Integer> temp=new ArrayList();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    list.add(temp);
                    j++;
                    k--;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> al = ts.threeSum(nums);
        System.out.println(al.toString());
    }

}

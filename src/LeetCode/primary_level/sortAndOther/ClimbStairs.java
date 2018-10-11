package LeetCode.primary_level.sortAndOther;

import java.util.ArrayList;

public class ClimbStairs {

    /*private int sum=0;
    //递归会导致时间复杂度很大
    public int climbStairs(int n) {
        if (n<=3) return sum+=n;
        climbStairs(n-1);
        climbStairs(n-2);
        return sum;
    }*/

    public int climbStairs(int n) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        for (int i=2;i<n;i++){
            al.add(al.get(i-2)+al.get(i-1));
        }
        return al.get(n-1);
    }

    public static void main(String args[]){
        ClimbStairs cs = new ClimbStairs();
        System.out.println(cs.climbStairs(5));
    }
}

package LeetCode.primary_level.sortAndOther;

public class ClimbStairs {

    private int sum=0;

    public int climbStairs(int n) {
        if (n<=3) return sum+=n;
        climbStairs(n-1);
        climbStairs(n-2);
        return sum;
    }

    public static void main(String args[]){
        ClimbStairs cs = new ClimbStairs();
        System.out.println(cs.climbStairs(5));
    }
}

package LeetCode.primary_level.sortAndOther;

public class isPowerOfThree {

    public boolean isPowerOfThree(int n) {
        if (n<=0)return false;
        int power = (int) Math.pow(3, (int) (Math.log(0x7fffffff) / Math.log(3)));
        if (power%n==0)return true;
        else return false;
    }

    public static void main(String[] args) {
        isPowerOfThree pt = new isPowerOfThree();
        System.out.println(pt.isPowerOfThree(81));
    }
}

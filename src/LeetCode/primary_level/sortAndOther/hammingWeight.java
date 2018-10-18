package LeetCode.primary_level.sortAndOther;

public class hammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        while(n>0){
            if (n%2==1)count++;
            n=n>>1;
        }
        return count;
    }

    public static void main(String[] args) {
        hammingWeight hw = new hammingWeight();
        System.out.println(hw.hammingWeight(  2147483647));
    }
}

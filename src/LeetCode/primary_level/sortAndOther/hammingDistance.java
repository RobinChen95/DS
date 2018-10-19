package LeetCode.primary_level.sortAndOther;

public class hammingDistance {
    public int hammingDistance(int x, int y) {
        int count=0;
        int endOfX;
        int endOfY;
        while (x>0||y>0){
            endOfX = x%2;
            endOfY = y%2;
            count+=endOfX^endOfY;
            x=x>>1;
            y=y>>1;
        }
        return count;
    }

    public static void main(String[] args) {
        hammingDistance hd = new hammingDistance();
        System.out.println(hd.hammingDistance(1,4));
    }
}

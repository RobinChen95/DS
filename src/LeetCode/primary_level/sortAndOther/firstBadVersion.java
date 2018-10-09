package LeetCode.primary_level.sortAndOther;

public class firstBadVersion {
    public int firstBadVersion(int n) {
        int min = 1, max = n, mid = 0;
        while(min <= max){
            mid = min + (max - min) / 2;
            if(isBadVersion(mid)){
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;

    }

    //test
    public boolean isBadVersion(int version) {
        return true;
    }
}

package LeetCode.primary_level.sortAndOther;

import java.util.Arrays;

public class countPrimes {
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                for (int j = i*2; j < n; j += i) {
                    prime[j]=false;
                }

            }
        }
        int count =0;
        for (int i = 2; i < n; i++) {
            if (prime[i])count++;
        }
        return count;
    }
}

package Chapter1_Basic;

import edu.princeton.cs.algs4.*;

public class Test {
    public static void main(String[] args){
        int[] a = new int[3];
        a[0]=1;
        a[1]=2;
        a[2]=3;
        for(int num : a){
            System.out.print(num);
        }
    }
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int min = prices[0];
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (min < prices[i]) {
                sum += prices[i] - min;
                min = prices[i];
            } else min = prices[i];
        }
        return sum;
    }
}

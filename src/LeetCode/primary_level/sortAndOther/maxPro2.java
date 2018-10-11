package LeetCode.primary_level.sortAndOther;

public class maxPro2 {
    public int maxProfit(int[] prices) {
        int pro=0;
        if (prices.length==0||prices.length==1)return 0;
        for (int i=0;i<prices.length-1;i++){
            if (prices[i]<prices[i+1])pro+=prices[i+1]-prices[i];
        }
        return pro;
    }
}

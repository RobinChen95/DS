package LeetCode.primary_level;

public class stockTrading {
    public int maxProfit(int[] prices){
        if (prices.length==0) return 0;
        int min = prices[0];
        int sum=0;
        for (int i=1;i<prices.length;i++){
            if(min<prices[i]){
                sum+=prices[i]-min;
                min=prices[i];
            }
            else min = prices[i];
        }
        return sum;
    }
    public static void main(String args[]){
        stockTrading st = new stockTrading();
        int[] test = {7,1,5,3,6,4};
        System.out.println(st.maxProfit(test));
    }
}

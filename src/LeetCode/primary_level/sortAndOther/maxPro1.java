package LeetCode.primary_level.sortAndOther;

public class maxPro1 {
    public int maxProfit(int[] prices) {
        if (prices.length<2)return 0;
        int min=prices[0];int max = 0;
        for (int i=0;i<prices.length;i++){
            max = Math.max(max,prices[i]-min);
            min = Math.min(min,prices[i]);
        }
        return max;
    }

    public static void main(String[] args){
        maxPro1 mp = new maxPro1();
        int[] a = {7,1,5,3,6,4};
        System.out.println(mp.maxProfit(a));
    }
}

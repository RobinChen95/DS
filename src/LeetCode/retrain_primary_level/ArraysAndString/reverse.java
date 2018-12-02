package LeetCode.retrain_primary_level.ArraysAndString;

/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。*/

//java int类型占32位

public class reverse {
    //此解法即为最佳
    public int reverse(int x) {
        if (x>0){
            return deal(x);
        }
        else if (x<0){
            x=Math.abs(x);
            return (-1)*deal(x);
        }
        return 0;
    }

    public int deal(int n){
        long sum = 0;
        int bit;
        while(n%10==0){
            n/=10;
        }
        while(n>0){
            bit = n%10;
            sum = (sum+bit)*10;
            n/=10;
        }
        sum/=10;
        if (sum>Integer.MAX_VALUE){
            return 0;
        }
        return (int)sum;
    }

    public static void main(String[] args) {
        reverse r = new reverse();
        System.out.println(r.reverse(-2147447413));
    }
}

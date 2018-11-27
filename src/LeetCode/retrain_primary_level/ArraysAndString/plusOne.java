package LeetCode.retrain_primary_level.ArraysAndString;

import java.util.ArrayList;
import java.util.Arrays;

public class plusOne {
    //此方法会报错，不能现转换为int或者long类型再+1
    public int[] plusOne1(int[] digits) {
       long l =0;
        for (int i = 0; i < digits.length; i++) {
            l+=digits[digits.length-i-1]*Math.pow(10,i);
        }
        l++;
        ArrayList<Integer> al = new ArrayList<>();
        while(l>=10){
            // 此处用al.add(((int)l%10)会出错
            al.add((Integer.parseInt(Long.toString(l%10))));
            l/=10;
        }
        if (l!=0)al.add((int)l);
        int[] a = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            a[i]= al.get(al.size()-i-1);
        }
        return a;
    }

    public int[] plusOne2(int[] digits){
        ArrayList<Integer> al = new ArrayList<>();
        //检查进位
        boolean[] check = new boolean[digits.length];
        //尾号加一
        digits[digits.length-1]++;
        if (digits[digits.length-1]==10){
            check[digits.length-1]=true;
            digits[digits.length-1]=0;
        }
        al.add(digits[digits.length-1]);
        for (int i = digits.length-1; i > 0; i--) {
            if (check[i]){
                //如果i进位
                digits[i-1]++;
            }
            if (digits[i-1]==10){
                digits[i-1]=0;
                //如果i位进位，将check[i-1]置为true
                check[i-1]=true;
            }
            al.add(digits[i-1]);
        }
        if (check[0])al.add(1);
        int[] result = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            result[i] = al.get(al.size()-i-1);
        }
        return result;
    }

    //github上的最佳解法
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0 ; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {6,1,4,5,3,9,0,1,9,5,1,8,6,7,0,5,5,4,3};
        int[] a2 = {9};
        plusOne po = new plusOne();
        System.out.println(Arrays.toString(po.plusOne1(a1)));
        System.out.println(Arrays.toString(po.plusOne2(a2)));
    }
}

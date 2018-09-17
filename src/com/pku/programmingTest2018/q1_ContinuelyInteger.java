package com.pku.programmingTest2018;

import java.util.Arrays;

public class q1_ContinuelyInteger {
    public static void main(String args[]){
        q1_ContinuelyInteger q1 = new q1_ContinuelyInteger();
        int target = 50;boolean none = true;
        System.out.println("输入："+target);
        System.out.println("输出：");
        for (int i=(target/2);i>1;i--){
            if (q1.divide(target,i)[0]>0){
                for (int j=0;j<q1.divide(target,i).length;j++){
                    System.out.print(q1.divide(target,i)[j]+" ");
                }
                System.out.println();
                none = false;
            }
        }
        if (none)System.out.println("None");
    }

    public int[] divide(int tar,int n){
        //根据等差数列的求和公式，如果能整除，说明存在连续数
        int[] array = new int[n];
        int a1 = 2*tar-n*n+n;  //分子
        int b1 = 2*n;  //分母
        if (isInteger(a1,b1)){
            int start = (a1/b1);
            for (int i=0;i<n;i++){array[i]=start+i;}
            return array;
        }
        else{   array[0]=-1;
                return array;}
    }

    public boolean isInteger(int a, int b){
        if (a==0 | b ==0) return false;
        if ((a%b)==0) return true;
        return false;
    }
}

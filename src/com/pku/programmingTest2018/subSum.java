package com.pku.programmingTest2018;

import java.util.Scanner;

public class subSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        subSum ss = new subSum();
        System.out.print(ss.subsum(n,array));
        sc.close();
    }

    public int subsum(int n, int[] a){
        int sum = Integer.MIN_VALUE;
        int peak = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (sum<0) sum=a[i];
            else sum+=a[i];
            if (sum>peak)peak=sum;
            /*System.out.println();
            System.out.println("a[i]:"+a[i]);
            System.out.println("sum:"+sum);
            System.out.println("peak"+peak);
            System.out.println();*/
        }
        return peak;
    }
}

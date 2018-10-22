package com.pku.programmingTest2018;

import java.util.Scanner;

public class moveRedundant {

    public int length(int[] a) {
        int temp = a[0];
        int count = 1;
        int redundant = 1;
        for (int i = 1; i < a.length; i++) {
            if (temp == a[i]) redundant++;
            else redundant = 1;
            if (redundant <= 2) count++;
            temp = a[i];
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        moveRedundant mr = new moveRedundant();
        System.out.print(mr.length(array));
        sc.close();
    }
}

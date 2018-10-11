package com.pku.programmingTest2018;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public long Fibo(int n){
        if (n==0)return 0;
        if (n==1)return 1;
        ArrayList<Long> al = new ArrayList<>();
        al.add(0l);al.add(1l);
        for (int i=1;i<n;i++){
            al.add(al.get(i-1)+al.get(i));
        }
        return al.get(n);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int val = sc.nextInt();
        Main M = new Main();
        System.out.println(M.Fibo(val));
    }
}
package com.pku.programmingTest2018;

import java.util.ArrayList;
import java.util.Scanner;

public class numberation {

    private ArrayList<Integer> al = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.close();
        numberation nb = new numberation();
        nb.transfer(m,n);
    }

    public void transfer(int num, int n) {
        while (num >= n) {
            al.add(num % n);
            num /= n;
        }
        al.add(num);
        for (int i = al.size() - 1; i >= 0; i--) {
            System.out.print(al.get(i));
        }
    }
}

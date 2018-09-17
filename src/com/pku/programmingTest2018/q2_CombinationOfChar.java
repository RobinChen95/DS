package com.pku.programmingTest2018;

import java.util.ArrayList;

public class q2_CombinationOfChar {
    private static int length;
    private ArrayList<Character> al1 = new ArrayList<Character>();

    public q2_CombinationOfChar(String s){
        for (int i=0;i<s.length();i++){
            al1.add(s.charAt(i));
        }
    }
    public  static void main(String args[]){
        String s = "abc";
        ArrayList<Character> al = new ArrayList<Character>();
        for (int i=0;i<s.length();i++){
            al.add(s.charAt(i));
        }
        length = s.length();
        q2_CombinationOfChar q2 = new q2_CombinationOfChar("abc");
        q2.prim(al);
    }

    public void prim(ArrayList<Character> al){
        //利用递归实现全排列
        if (al.size()==1) {System.out.print(al.get(0)+", "); return;}
        else {
            for (int i=0;i<al.size();i++){
                ArrayList<Character> temp = new ArrayList<Character>();
                for (int j=0;j<al.size();j++){temp.add(al.get(j));}
                System.out.print(al.get(i));
                temp.remove(i);
                //System.out.println("temp1:"+temp);
                prim(temp);
                temp.add(i,al.get(i));
            }
        }
    }
}

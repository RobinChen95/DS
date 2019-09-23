package Test;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i ++) {
            res <<= 1;
            res += n & 1;
            n >>= 1;
        }
        return res;
    }

    public int hammingDistance(int x, int y) {
        int count=0;
        for (int i = 0; i < 31; i++) {
            count+=(x>>i&1)&(y>>i&1);
        }
        return count;
    }

    public int hammingWeight(int n) {
        int count=0;
        for (int i = 0; i < 31; i++) {
            count+=(n>>>i&1);
        }
        return count;
    }

    public boolean isValid(String s) {
        try {
            if (s == "") return true;
            if (s.length() == 1) return false;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                    stack.push(s.charAt(i));
                } else if (stack.peek() == null) return true;
                else {
                    char temp = stack.pop();
                    switch (temp) {
                        case '{':
                            if (s.charAt(i) == '}') continue;
                            else return false;
                        case '[':
                            if (s.charAt(i) == ']') continue;
                            else return false;
                        case '(':
                            if (s.charAt(i) == ')') continue;
                            else return false;
                    }

                }
            }
            if (stack.isEmpty())return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<List<Integer>> generate(int numRows) {
        if (numRows<=0)return new ArrayList<>();
        List<List<Integer>> ll = new ArrayList<>();
        ll.add(new ArrayList<>());
        ll.get(0).add(1);
        if (numRows==1)return ll;
        ll.add(new ArrayList<>());
        ll.get(1).add(1);
        ll.get(1).add(1);
        if (numRows==2)return ll;
        // 从第三行开始
        for (int i = 3; i <= numRows; i++) {
            List temp = new ArrayList();
            for (int j = 0; j < i; j++) {
                if (j==0||j==i-1) temp.add(1);
                else temp.add(ll.get(i-2).get(j-1)+ll.get(i-2).get(j));
            }
            ll.add(temp);
        }
        return ll;
    }

    public static void main(String[] args) {
        String test = "";
        Solution solution = new Solution();
        System.out.println(solution.isValid(test));
    }
}

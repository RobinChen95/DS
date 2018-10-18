package Chapter1_Basic;


import java.util.ArrayList;
import java.util.Stack;

public class Test {
    public static boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            String s1 = String.valueOf(s.charAt(i));
            String pop;
            if ("(".equals(s1) || "{".equals(s1) || "[".equals(s1)) {
                stack.push(s1);
            } else if (")".equals(s1)) {
                if (stack.size() < 1) {
                    return false;
                } else {
                    pop = (String) stack.pop();
                }
                if (!"(".equals(pop)) {
                    return false;
                }
            } else if ("}".equals(s1)) {
                if (stack.size() < 1) {
                    return false;
                } else {
                    pop = (String) stack.pop();
                }
                if (!"{".equals(pop)) {
                    return false;
                }
            } else if ("]".equals(s1)) {
                if (stack.size() < 1) {
                    return false;
                } else {
                    pop = (String) stack.pop();
                }
                if (!"[".equals(pop)) {
                    return false;
                }
            }
        }
        if (!stack.empty()) {
            return false;
        }
        return true;
    }

}
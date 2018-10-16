package LeetCode.primary_level.sortAndOther;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> al = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i%3==0&&i%5==0) al.add("FizzBuzz");
            else if (i%3==0)al.add("Fizz");
            else if (i%5==0)al.add("Buzz");
            else al.add(String.valueOf(i));
        }
        return al;
    }

    public static void main(String[] args) {
        fizzBuzz fb = new fizzBuzz();
        System.out.println(Arrays.toString(fb.fizzBuzz(15).toArray()));
    }
}

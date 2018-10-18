package LeetCode.primary_level.sortAndOther;

import java.util.HashMap;

public class romanToInt {

    public int romanToInt(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = map.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                sum += map.get(s.charAt(i));
            else
                sum -= map.get(s.charAt(i));
        }
        return sum;
    }

    public static void main(String[] args) {
        String str = "MCMXCIV";
        romanToInt ri = new romanToInt();
        System.out.println(ri.romanToInt(str));
    }
}

package LeetCode.middle_level;

public class longestPalindrome {
    public static void main(String[] args) {
        longestPalindrome lpd = new longestPalindrome();
        System.out.println(lpd.longestPalindrome("abb"));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) return s;
        for (int i = s.length(); i >= 1; i--) {
            for (int j = 0; j + i <= s.length() ; j++) {
                if (isPalindrome(s.substring(j, j+i))) {
                    return s.substring(j, j+i);
                }
            }
        }
        return "";
    }

    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (j > i) {
            if (s.charAt(i) == s.charAt(j)) {
                j--;
                i++;
            } else {
                return false;
            }
        }
        return true;
    }
}

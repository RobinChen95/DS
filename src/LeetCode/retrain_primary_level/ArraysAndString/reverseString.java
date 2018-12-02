package LeetCode.retrain_primary_level.ArraysAndString;

public class reverseString {
    //第一种解法StringBuilder
    public String reverseString1(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    //第二种解法StringBuffer
    public String reverseString2(String s){
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString();
    }

    //第三种解法
    public String reverseString3(String s){
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length()/2; i++) {
            swap(str,i,str.length-1-i);
        }
        return String.valueOf(str);
    }

    private void swap(char[] chars, int i ,int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] =temp;

    }

    public static void main(String[] args) {
        String str1 = new String("A man, a plan, a canal: Panama");
        String str2 = new String("A man, a plan, a canal: Panama");
        String str3 = new String("A man, a plan, a canal: Panama");
        reverseString rs = new reverseString();
        System.out.println(rs.reverseString1(str1));
        System.out.println(rs.reverseString1(str2));
        System.out.println(rs.reverseString1(str3));
    }
}

package LeetCode.primary_level;

public class strStr {
    public static void main(String args[]){
        String first = "a";
        String second = "";
        strStr ss = new strStr();
        System.out.println(ss.strStr(first,second));
    }

    public int strStr(String haystack, String needle) {
        if (needle=="")return 0;
        if (haystack.length()==0&(needle.length()!=0))return -1;
        int count;int start = 0;
        for (int i=0;i<haystack.length();i++){
            count=0;
            if (haystack.charAt(i)==needle.charAt(0))
            for (int j=0;j<needle.length();j++){
                if (haystack.charAt(i+j)==needle.charAt(j))
                    count++;
                if (count==needle.length()) start=i;
            }
        }
        return start;
    }
}

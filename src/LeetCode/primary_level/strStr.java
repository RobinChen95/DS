package LeetCode.primary_level;

public class strStr {
    public static void main(String args[]){

    }

    public int strStr(String haystack, String needle) {
        if (needle=="")return 0;
        if (haystack.length()==0&(needle.length()!=0))return -1;
        int start=0;int end=0;
        for (int i=0;i<haystack.length();i++){
            if (haystack.charAt(i)==needle.charAt(0))
            for (int j=0;j<needle.length();j++){
                
            }
        }
    }
}

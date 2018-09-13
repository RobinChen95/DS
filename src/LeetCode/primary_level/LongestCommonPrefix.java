package LeetCode.primary_level;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // Search for the shortest String
        if(strs.length==0)return "";
        int min = strs[0].length();
        for (int i=1;i<strs.length;i++){
            if (min>strs[i].length()){
                min=strs[i].length();
            }
        }
        //System.out.println(strs[0]);
        char[] common = new char[min];
        for (int i=0;i<min;i++){
            common[i] = strs[0].charAt(i);//注意charAt函数的起始位
        }
        //System.out.println(String.valueOf(common));
        for(int i=1;i<strs.length;i++){
            for(int j=0;j<min;j++){
                //System.out.println(String.valueOf(common));
                if(common[j]!=strs[i].charAt(j))
                     {common[j]=0;break;}
            }
        }
        int n = 0;
        for (int i=0;i<min;i++){
            if(common[i]!=0){n++;}
            else break;
        }
        char[] target = new char[n];
        for (int i=0;i<n;i++){
            target[i]=common[i];
        }
        //System.out.println(target[0]);
        if (target.length==0){return "";}
        else return String.valueOf(target);
    }

    public static void main(String args[]){
        String[] test = {};
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println(lcp.longestCommonPrefix(test));
    }
}

package LeetCode.primary_level.ArraysAndString;
/* 引用其他人的描述：
https://www.cnblogs.com/ariel-dreamland/p/9138400.html

报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
1.     1
2.     11
3.     21
4.     1211
5.     111221
1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

给定一个正整数 n ，输出报数序列的第 n 项。

注意：整数顺序将表示为一个字符串。

示例 1:

输入: 1
输出: "1"
示例 2:

输入: 4
输出: "1211"
解题思路：

题目描述的不是很清楚，其实就是第i+1个字符串是第i个字符串的读法，第一字符串为 “1”

比如第四个字符串是1211，它的读法是 1个1、1个2,2个1，因此第五个字符串是111221。

第五个字符串的读法是：3个1、2个2、1个1，因此第六个字符串是312211

**/
public class countAndSay {
    public static  void main(String args[]){
        countAndSay cs = new countAndSay();
        System.out.println(cs.countAndSay(7));
    }
    public String countAndSay(int n) {
        //pre area
        if (n==1)return "1";
        if (n==2)return "11";
        if (n==3)return "21";
        if (n==4)return "1211";
        //pre area
        int count = 5;
        int repeat;
        String init = "1211";
        while (count<=n){
            repeat=1;
            int digits=init.charAt(0)-48;
            String temp="";
            for (int i=1;i<init.length();i++){
                if (digits==init.charAt(i)-48){
                    if (i==init.length()-1){
                        repeat++;
                        temp+=String.valueOf(repeat)+""+String.valueOf(digits);
                        repeat=1;
                        continue;
                    }
                    else repeat++;
                }
                else {
                    temp+=String.valueOf(repeat)+""+String.valueOf(digits);
                    digits=init.charAt(i)-48;
                    repeat=1;
                    if (i==init.length()-1){
                        temp+=String.valueOf(1)+""+String.valueOf(init.charAt(i)-48);
                    }
                }
            }
            count++;
            init=temp;
        }
        return init;
    }
}

package Chapter1_Basic;

/*
* 关于哥德巴赫猜想的穷举法证明
* 做了断点续训
* 性能需要
*   1、多线程优化
* */

import java.io.*;
import java.math.BigInteger;

public class GoldBach {
    //建立备忘录队列
    static BigInteger[] latest_used = new BigInteger[100];
    static int pointer = -1;

    public static void main(String[] args) {
        for (int i = 0; i < latest_used.length; i++) {
            latest_used[i]=BigInteger.valueOf(0);
        }
        try {
            BigInteger bigInteger = new BigInteger("123232318");
            FileReader fr = new FileReader("./GoldBach.txt");
            BufferedReader br = new BufferedReader(fr);
            String savedNum = br.readLine();
            if (savedNum!=null)
            bigInteger= new BigInteger(savedNum);
            boolean found;
            int count=0;
        while(true){
            found = false;
            for (BigInteger bi = BigInteger.valueOf(3);bi.compareTo(bigInteger.divide(BigInteger.valueOf(2)))<=0;
            bi = bi.add(BigInteger.valueOf(2))){
                BigInteger b = bigInteger.subtract(bi);
                if (isPrime(bi)&&isPrime(b)){
                    //只找一个满足条件的即可退出
                    found = true;
                    count++;
                    //一万轮打印并保存一次
                    if (count>10000){
                        FileOutputStream fos = new FileOutputStream("./GoldBach.txt",false);
                        fos.write(bigInteger.toString().getBytes());
                        fos.close();
                        System.out.println(bigInteger+"="+bi+"+"+b);
                        count=0;
                    }
                    break;
                }
            }
            bigInteger = bigInteger.add(BigInteger.valueOf(2));
            //如果没有找到，即哥德巴赫猜想错误
            if (!found){
                System.out.println(bigInteger);
                System.exit(0);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isPrime(BigInteger bi){
        //大于10000时查询备忘录
        if (bi.compareTo(BigInteger.valueOf(10000))>0){
            for (int i = 0; i < latest_used.length; i++) {
                if (bi.compareTo(latest_used[i])==0){return true;}
            }
        }
        for (BigInteger bigInteger=BigInteger.valueOf(2);bigInteger.compareTo(bi.sqrt())<=0;
             bigInteger=bigInteger.add(BigInteger.valueOf(1))) {
            if (bi.mod(bigInteger)==BigInteger.valueOf(0)){return false;}
        }
        //大于10000时存入备忘录队列
        if (bi.compareTo(BigInteger.valueOf(10000))>0){
            pointer+=1;
            pointer = pointer%100;
            latest_used[pointer] = bi;
        }
        return true;
    }

}

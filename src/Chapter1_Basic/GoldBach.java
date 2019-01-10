package Chapter1_Basic;

import java.math.BigInteger;

public class GoldBach {

    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("8");
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
                    //一万轮打印一次
                    if (count>10000){
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

    }

    public static boolean isPrime(BigInteger bi){
        for (BigInteger bigInteger=BigInteger.valueOf(2);bigInteger.compareTo(bi.sqrt())<=0;
             bigInteger=bigInteger.add(BigInteger.valueOf(1))) {
            //System.out.println(bi);
            if (bi.mod(bigInteger)==BigInteger.valueOf(0)){return false;}
        }
        return true;
    }
}

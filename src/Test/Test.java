package Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Test implements Runnable {

    int n = 5;

    @Override
    public void run() {
        while(n!=0){
            System.out.println("the Thread never stops!");
            System.out.println("n:"+n);
            n--;
        }
    }

    public static void main(String[] args) {
        Test t1 = new Test();
        t1.run();
        Test t2 = t1;
        t2.run();
    }
}
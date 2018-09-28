package AlgorithmCoursePKU;

/*
 * Java图形实现汉诺塔问题
 * */
public class hanoi {
    private int times;
    private int[] chipOfA;
    private int[] chipOfB;
    private int[] chipOfC;

    public void setTimes(int time) {
        if (time > 15) System.out.println("Wrong Input!");//不能大于15行
        else times = time;
    }

    public int getTimes() {
        return times;
    }

    public hanoi(int kk) {
        setTimes(kk);
        chipOfA = new int[kk];
        chipOfB = new int[kk];
        chipOfC = new int[kk];
        for (int i=0;i<kk;i++){
            chipOfA[i]=i+1;
            chipOfB[i]=0;
            chipOfC[i]=0;
        }
    }

    public static void main(String args[]) {
        hanoi hi = new hanoi(8);
        for (int i = 0; i < hi.getTimes(); i++) {
            System.out.println(i + 1);
            hi.init(101);
        }
    }

    public void printChips(){

    }

    public void init(int n) {
        System.out.println("\n");
        for (int i = 0; i <= n; i++) {
            System.out.print("-");
        }
        System.out.println("\n");//缩进两行

        int count = 15-chipOfA.length-1;
        int ptr = 0;
        for (int i = 0; i < (n / 3 / 2) - 1; i++) {  //板长31，间距4
            System.out.print("               ");
            if (i>count){
                for (int j=0;j<chipOfA[ptr];j++){//退格以便打印
                    System.out.print("\b");
                }
                //System.out.print("退格："+cishu+"次");
                for (int j=0;j<chipOfA[ptr];j++){
                    System.out.print("-");
                }
            }
            System.out.print("|");//print a |，总共15行|
            if (i>count){
                for (int j=0;j<chipOfA[ptr];j++){
                    System.out.print("-");
                }

            }

            System.out.print("               ");
            System.out.print("    ");

            System.out.print("               ");
            if (i>count){
                for (int j=0;j<chipOfA[ptr];j++){//退格以便打印
                    System.out.print("\b");
                }
            }
            System.out.print("|");//print b |，总共15行|
            if (i>count){
            }
            System.out.print("               ");
            System.out.print("    ");

            System.out.print("               ");
            if (i>count){

            }
            System.out.print("|");//print c |，总共15行|
            if (i>count){
                ptr++;
            }
            System.out.print("               ");
            System.out.print("    ");
            System.out.println();

        }


        for (int i = 0; i < n - 8; i += 3) {
            System.out.print("-");
        }
        System.out.print("\t\t");// 4 spaces
        for (int i = 0; i < n - 8; i += 3) {
            System.out.print("-");
        }
        System.out.print("\t\t");// 4 spaces
        for (int i = 0; i < n - 8; i += 3) {
            System.out.print("-");
        }
        System.out.println();
        char a = 'a';
        for (int i = 15; i < 100; i += 31) {
            System.out.print("\t\t\t\t");
            System.out.print(a++);
            System.out.print("\t\t\t\t\t");
        }
        System.out.println("\n");
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }


}

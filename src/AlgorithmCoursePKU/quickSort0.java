package AlgorithmCoursePKU;

import java.util.Arrays;

public class quickSort0 {
    public int[] sort (int[] A){
        int i = 0;
        int j = A.length-1;
        while (i<j){
            while (A[i]<0)i++;
            while (A[j]>0)j--;
            swap(A,i,j);
        }
        swap(A,i,j);
        return A;
    }

    public void swap(int[] A,int p, int q){
        int temp;
        temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }

    public static void main(String[] args){
        quickSort0 qs0 = new quickSort0();
        int[] a = {-4,5,-2,1,6,-9};
        System.out.println(Arrays.toString(qs0.sort(a)));
    }
}

package LeetCode.primary_level;

import java.util.Arrays;

public class rotate_array {

    public void swap(int[] array,int i ,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int[] rotate(int[] arr, int k){

        for(int i=0;i<(arr.length-k)/2;i++){
            swap(arr,i,arr.length-k-i-1);
        }
        for(int i=0;i<k/2;i++){
            swap(arr,arr.length-k+i,arr.length-i-1);
        }
        for(int i=0;i<arr.length/2;i++){
            swap(arr,i,arr.length-i-1);
        }
        return arr;
    }

    public static void main(String args[]){
        rotate_array ra = new rotate_array();
        int[] array = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(ra.rotate(array,3)));
    }
}

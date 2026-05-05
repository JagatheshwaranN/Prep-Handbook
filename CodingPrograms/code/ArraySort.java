package programs;

import java.util.*;
public class ArraySort {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 1, 5, 4};

        int arrLen = arr.length;

        for(int i = 0; i < arrLen; i++) {

            for(int j = i+1; j < arrLen; j++){

                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        int[] arr2 = new int[]{2, 3, 1, 5, 4};

        int arr2Len = arr2.length;

        for(int i = 0; i < arr2Len; i++) {

            for(int j = i+1; j < arr2Len; j++){

                if(arr2[i] < arr2[j]){
                    int temp = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr2));
    }

}
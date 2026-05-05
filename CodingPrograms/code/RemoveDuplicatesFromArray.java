package programs;

import java.util.Arrays;
import java.util.HashSet;

public class RemoveDuplicatesFromArray {

    public static int[] input = {4, 3, 2, 4, 8, 9, 2};
    public static void main(String[] args) {
        removeDuplicates(input);
        removeDuplicatesUsingCollection(input);
    }

    static void removeDuplicates(int[] arr){

        int uniqueElements = arr.length;
        int j;

        for(int i = 0; i < uniqueElements; i++){

            for(j = i+1; j < uniqueElements; j++){
                    if(arr[i] == arr[j]){
                        arr[j] = arr[uniqueElements-1];
                        uniqueElements--;
                        j--;
                    }
                System.out.println(Arrays.toString(arr));
            }

        }
        int[] arrWithoutDuplicates = Arrays.copyOf(arr, uniqueElements);
        System.out.println(Arrays.toString(arrWithoutDuplicates));
    }

    static void removeDuplicatesUsingCollection(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        int[] newArr = new int[arr.length];
        int index = 0;
        for(int a : arr){
            if(set.add(a)){
                newArr[index++] = a;
            }
        }
        System.out.println(Arrays.toString(newArr));
        int[] finalArr = new int[index];
        System.arraycopy(newArr, 0, finalArr, 0, index);
        System.out.println(Arrays.toString(finalArr));
    }

}

package programs;

import java.util.Arrays;

public class SelectionSort {

    public static int[] input = {62, -45, 14, 8, 5, 14, -52, 45};

    public static void main(String[] args) {
        selectionSort();
    }

    static void selectionSort() {

        int arrLength = input.length;
        for (int i = 0; i < arrLength; i++) {

            int minIndex = i;
            for (int j = i + 1; j < arrLength; j++) {

                if (input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = input[i];
                input[i] = input[minIndex];
                input[minIndex] = temp;
            }
        }
        System.out.println(Arrays.toString(input));
    }

}

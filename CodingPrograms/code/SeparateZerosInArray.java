package programs;

import java.util.Arrays;

public class SeparateZerosInArray {

    public static int[] input = {14, 0, 5, 2, 0, 3, 0};

    public static void main(String[] args) {
        separateZerosToEnd();
        separateZerosToFront();
    }

    static void separateZerosToEnd() {

        int counter = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 0) {
                input[counter] = input[i];
                counter++;
            }
        }
        while (counter < input.length) {
            input[counter] = 0;
            counter++;
        }
        System.out.println(Arrays.toString(input));
    }

    static void separateZerosToFront() {

        int counter = input.length - 1;
        for(int i = input.length -1; i >=0 ; i--){
            if(input[i] != 0){
                input[counter] = input[i];
                counter--;
            }
        }
        while(counter >= 0){
            input[counter] = 0;
            counter--;
        }
        System.out.println(Arrays.toString(input));
    }

}

package programs;

import java.util.Arrays;

public class ReverseStringWithPreservedSpace {

    public static String input = "I am not a string";

    public static void main(String[] args) {
        System.out.println(input);
        reverseStringWithSpace();
    }

    static void reverseStringWithSpace() {

        char[] inputArray = input.toCharArray();
        char[] resultArray = new char[inputArray.length];

        for(int i = 0; i < inputArray.length; i++){
            if(inputArray[i] == ' '){
                resultArray[i] = ' ';
            }
        }

        int j = resultArray.length - 1;

        for(int i = 0; i < inputArray.length; i++) {

            if(inputArray[i] != ' '){

                if(resultArray[j] == ' '){
                    j--;
                }
                resultArray[j] = inputArray[i];
                j--;
            }
        }

        System.out.println(new String(resultArray));
    }

}

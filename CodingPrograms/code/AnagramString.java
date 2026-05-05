package programs;

import java.util.Arrays;
import java.util.HashMap;

public class AnagramString {

    public static String input1 = "Dormitory";
    public static String input2 = "Dirty Room";

    public static void main(String[] args) {
        isAnagramString(input1, input2);
        isAnagramString("Joy", "Enjoy");
    }

    static void isAnagramString(String input1, String input2) {

        String input1Copy = input1.replaceAll("\\s+", "");
        String input2Copy = input2.replaceAll("\\s+", "");

        boolean status;

        if (input1Copy.length() != input2Copy.length()) {
            status = false;
        } else {
            char[] input1CharArr = input1Copy.toCharArray();
            char[] input2CharArr = input1Copy.toCharArray();
            Arrays.sort(input1CharArr);
            Arrays.sort(input2CharArr);
            status = Arrays.equals(input1CharArr, input2CharArr);
        }
        if (status) {
            System.out.println(input1 + " and " + input2 + " are anagram strings");
        } else {
            System.out.println(input1 + " and " + input2 + " are not anagram strings");
        }
    }

}

package programs;

public class ReverseString {

    public static String input = "java";

    public static void main(String[] args) {
        reverseUsingStringBuffer();
        reverseUsingStringBuilder();
        reverseUsingCharAt();
        reverseUsingCharArray();
        System.out.println(reverseUsingRecursive(input));
    }

    static void reverseUsingStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer(input);
        System.out.println(stringBuffer.reverse());
    }

    static void reverseUsingStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder(input);
        System.out.println(stringBuilder.reverse());
    }

    static void reverseUsingCharAt() {
        for (int i = input.length() - 1; i >= 0; i--) {
            System.out.print(input.charAt(i));
        }
        System.out.println();
    }

    static void reverseUsingCharArray() {
        char[] inputCharArr = input.toCharArray();
        for (int i = inputCharArr.length - 1; i >= 0; i--) {
            System.out.print(inputCharArr[i]);
        }
        System.out.println();
    }

    static String reverseUsingRecursive(String input) {
        if (input == null || input.length() < 1) {
            return input;
        }
        return reverseUsingRecursive(input.substring(1)) + input.charAt(0);
    }


}

package programs;

public class ReverseEachWordOfString {

    public static String input = "Java programs for the day";

    public static void main(String[] args) {
        reverseWordsInStringUsingFor();
        reverseWordsInStringUsingWhile();
    }

    static void reverseWordsInStringUsingFor() {

        String reverseString = "";
        String[] words = input.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reverseWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {
                reverseWord = reverseWord + word.charAt(j);
            }
            reverseString = reverseString + reverseWord + " ";
        }

        System.out.println(reverseString);
    }

    static void reverseWordsInStringUsingWhile() {

        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();
        for(String word : words){
            result.append(reverseEachWord(word)).append(" ");
        }
        System.out.println(result);
    }

    private static String reverseEachWord(String word) {

        char[] lettersArray = word.toCharArray();
        int start = 0;
        int end = lettersArray.length - 1;
        while(start < end) {
            char temp = lettersArray[start];
            lettersArray[start] = lettersArray[end];
            lettersArray[end] = temp;
            start++;
            end--;
        }
        return new String(lettersArray);
    }

}

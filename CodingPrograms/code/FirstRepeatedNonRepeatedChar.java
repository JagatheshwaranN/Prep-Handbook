package programs;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class FirstRepeatedNonRepeatedChar {

    public static String input = "python concept of the day";

    public static void main(String[] args) {
        findNonRepeatedAndRepeatedCharUsingMap();
        findNonRepeatedAndRepeatedChar();
    }

    static void findNonRepeatedAndRepeatedCharUsingMap() {

        String inputWithoutSpace = input.replaceAll("\\s", "").trim();
        char[] inputCharArr = inputWithoutSpace.toCharArray();
        LinkedHashMap<Character, Integer> charCountMap = new LinkedHashMap<>();
        for (char ch : inputCharArr) {

            if (charCountMap.containsKey(ch)) {
                charCountMap.put(ch, charCountMap.get(ch) + 1);
            } else {
                charCountMap.put(ch, 1);
            }
        }
        for (char c : charCountMap.keySet()) {
            if (charCountMap.get(c) == 1) {
                System.out.println("First Non Repeated Character : " + c);
                break;
            }
        }
        for (char c : charCountMap.keySet()) {
            if (charCountMap.get(c) > 1) {
                System.out.println("First Repeated Character : " + c);
                break;
            }
        }
    }

    static void findNonRepeatedAndRepeatedChar() {

        String inputWithoutSpace = input.replaceAll("\\s", "").trim();
        char[] inputCharArr = inputWithoutSpace.toCharArray();
        char[] result = new char[2];
        int[] charCount = new int[256];

        for (char c : inputCharArr) {
            charCount[c]++;
        }

        for (char c : inputCharArr) {

            if (charCount[c] > 1 && result[0] == '\0') {
                result[0] = c;
                System.out.println("First Repeated Character : " + c);
            } else if (charCount[c] == 1 && result[1] == '\0') {
                result[1] = c;
                System.out.println("First Non Repeated Character : " + c);
            }
            if (result[0] != '\0' && result[1] != '\0') {
                break;
            }
        }
    }

}

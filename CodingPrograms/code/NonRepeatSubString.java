package programs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class NonRepeatSubString {

    public static String input = "javaprogramoftheday";
    public static void main(String[] args) {
        findLongestSubString();
    }

    static void findLongestSubString() {
        String output = "";
        for (int start = 0; start < input.length(); start++) {
            HashSet<Character> visited = new HashSet<>();
            int end;
            for (end = start; end < input.length(); end++) {
                char currChar = input.charAt(end);
                if (visited.contains(currChar)) {
                    break;
                } else {
                    visited.add(currChar);
                }
            }
            System.out.println(visited);
            if (output.length() < end - start + 1) {
                output = input.substring(start, end);
                System.out.println("Output : "+ output);

            }
        }
        System.out.println(output);
    }

}

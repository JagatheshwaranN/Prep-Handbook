package programs;

import java.util.HashSet;

public class PrintCommonChars {

    public static String input1 = "Program of the day";
    public static String input2 = "Paragraph for today";

    public static void main(String[] args) {
        findCommonChars();
    }

    static void findCommonChars() {
        char[] input1CharArr = input1.toLowerCase().replaceAll("\\s+", "").toCharArray();
        char[] input2CharArr = input2.toLowerCase().replaceAll("\\s+", "").toCharArray();

        HashSet<Character> visited = new HashSet<>();

        for (int i = 0; i < input1CharArr.length; i++) {

            for (int j = 0; j < input2CharArr.length; j++) {

                if (input1CharArr[i] == input2CharArr[j]) {

                    if (visited.contains(input1CharArr[i])) {
                        break;
                    } else {
                        visited.add(input1CharArr[i]);
                    }
                }
            }
        }
        System.out.println(visited);
    }
}

package programs;

public class StringPermutation {

    public static String input = "ABC";

    public static void main(String[] args) {

        PermutationOfString(input);

    }

    static void PermutationOfString(String input) {
        findPermutation("", input);
    }

    static void findPermutation(String permutation, String input) {
        if (input.length() == 0) {
            System.out.println(permutation);
        } else {
            for (int i = 0; i < input.length(); i++) {
                findPermutation(permutation + input.charAt(i), input.substring(0, i) + input.substring(i + 1, input.length()));
            }
        }
    }
}

package programs;

public class RemoveWhiteSpaceFromString {

    public static String input = "OneSpace TwoSpaces  ThreeSpaces   FourSpaces    Tab        End";

    public static void main(String[] args) {
        removeSpace();
    }

    static void removeSpace() {
        input = input.replaceAll("\\s+", "");
        System.out.println(input);
    }
}

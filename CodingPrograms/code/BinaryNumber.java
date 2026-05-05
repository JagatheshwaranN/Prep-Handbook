package programs;

public class BinaryNumber {

    public static void main(String[] args) {
        System.out.println(findBinaryOrNotUsingWhile(10101));
        System.out.println(findBinaryOrNotUsingFor("10101"));
        System.out.println(findBinaryUsingRegex("10101"));
    }

    static boolean findBinaryOrNotUsingWhile(int num) {

        while (num != 0) {
            int digit = num % 10;
            if (digit != 0 && digit != 1) {
                return false;
            }
            num = num / 10;
        }
        return true;
    }

    static boolean findBinaryOrNotUsingFor(String number) {

        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            if (digit != '0' && digit != '1') {
                return false;
            }
        }
        return true;
    }

    static boolean findBinaryUsingRegex(String number){
        String regex = "[01]+";
        return number.matches(regex);
    }

}

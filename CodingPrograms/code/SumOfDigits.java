package programs;

public class SumOfDigits {

    public static int input = 98765;

    public static void main(String[] args) {
        sumOfDigits();
        int sum = sumOfDigitsUsingRecursion(input);
        System.out.println(sum);
    }

    static void sumOfDigits() {
        int inputCopy = input;
        int sum = 0;
        while (inputCopy != 0) {
            int remainder = inputCopy % 10;
            sum = sum + remainder;
            inputCopy = inputCopy / 10;
        }
        System.out.println(sum);
    }

    static int sumOfDigitsUsingRecursion(int input) {
        if (input == 0) {
            return 0;
        } else {
            return (input % 10) + sumOfDigitsUsingRecursion(input / 10);
        }
    }

}

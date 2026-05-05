package programs;

import java.util.Scanner;

public class ReverseAndAddUntilGetPalindrome {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        reverseAndAddTillPalindrome(input);
    }

    private static void reverseAndAddTillPalindrome(int number) {

        if(checkPalindrome(number)){
            System.out.println(number + " is already a palindrome number");
        }else{
            int sum;
            while(!checkPalindrome(number)){
                int reverse = reverseNumber(number);
                sum = number + reverse;
                System.out.println(number + " + "+reverse+" = "+sum);
                number = sum;
            }
        }
    }

    private static boolean checkPalindrome(int number) {

        return number == reverseNumber(number);
    }

    private static int reverseNumber(int number) {

        int reverse = 0;
        while(number != 0){
            int remainder = number % 10;
            reverse = (reverse * 10) + remainder;
            number = number / 10;
        }
        return reverse;
    }

}

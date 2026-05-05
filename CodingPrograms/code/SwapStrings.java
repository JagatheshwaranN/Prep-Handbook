package programs;

import java.util.Scanner;

public class SwapStrings {

    public static String input1, input2;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first string");
        input1 = scanner.nextLine();
        System.out.println("Enter the second string");
        input2 = scanner.nextLine();
        doSwap();

    }

    private static void doSwap() {

        System.out.println("Strings before swap");
        System.out.println(input1);
        System.out.println(input2);
        input1 = input1 + input2;
        input2 = input1.substring(0, input1.length() - input2.length());
        input1 = input1.substring(input2.length());
        System.out.println("Strings after swap");
        System.out.println(input1);
        System.out.println(input2);
    }
}

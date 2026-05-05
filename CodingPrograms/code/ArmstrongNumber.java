package programs;

public class ArmstrongNumber {

    public static int input = 54748;
    public static void main(String[] args) {
        checkArmstrongNumber();
    }

    static void checkArmstrongNumber() {

        int inputCopy = input;
        int remainder;
        int sum = 0;
        int digits = 0;
        while(inputCopy != 0){
            inputCopy = inputCopy / 10;
            digits++;
        }

        inputCopy = input;

        while(inputCopy != 0){
            remainder = inputCopy % 10;
            sum = (int) (sum + Math.pow(remainder, digits));
            inputCopy = inputCopy / 10;
        }

        if(sum == input){
            System.out.println(input + " is a armstrong number");
        } else {
            System.out.println(input + " is not a armstrong number");
        }

    }


}

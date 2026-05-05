package programs;

public class FindMissingNumberInArray {

    public static int[] input = {1, 4, 5, 3, 7, 8, 6};
    public static void main(String[] args) {
        findMissingNumber();
    }

    static void findMissingNumber() {
        int n = input.length + 1;
        int sumOfNumbers = n * ( n + 1) / 2;
        int actualSum = 0;
        for(int element : input){
            actualSum = actualSum + element;
        }
        int missingNumber = sumOfNumbers - actualSum;
        System.out.println(missingNumber);
    }

}

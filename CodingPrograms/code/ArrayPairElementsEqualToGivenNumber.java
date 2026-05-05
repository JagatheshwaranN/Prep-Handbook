package programs;

public class ArrayPairElementsEqualToGivenNumber {

    public static void main(String[] args) {
        int[] input1 = {4, -5, 9, 11, 25, 13, 12, 8};
        int input2 = 20;
        findArrayPairsWhoseSumEqualToNumber(input1, input2);
    }

    static void findArrayPairsWhoseSumEqualToNumber(int[] arr, int number) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == number) {
                    System.out.println(arr[i] + "+" + arr[j] + "=" + number);
                }
            }
        }
    }

}

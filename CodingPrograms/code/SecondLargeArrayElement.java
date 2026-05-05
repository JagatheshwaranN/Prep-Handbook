package programs;

public class SecondLargeArrayElement {

    public static int[] inputArray = {8, 7, 13, 11, 14, 12, 15};

    public static void main(String[] args) {
        findSecondLargeArrayElement();
    }

    static void findSecondLargeArrayElement() {
        int firstLarge;
        int secondLarge;

        if (inputArray[0] > inputArray[1]) {
            firstLarge = inputArray[0];
            secondLarge = inputArray[1];
        } else {
            firstLarge = inputArray[1];
            secondLarge = inputArray[0];
        }

        for (int i = 2; i < inputArray.length; i++) {

            if (inputArray[i] > firstLarge) {
                secondLarge = firstLarge;
                firstLarge = inputArray[i];
            } else if (inputArray[i] < firstLarge && inputArray[i] > secondLarge) {
                secondLarge = inputArray[i];
            }
        }
        System.out.println(secondLarge);
    }

}

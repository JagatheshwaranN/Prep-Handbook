package programs;

public class LargestNumberWithoutGivenDigit {

    public static void main(String[] args) {
        int input1 = 6789;
        int input2 = 8;
        System.out.println(findLargestNumberWithoutGivenDigit(input1, input2));
    }

    static int findLargestNumberWithoutGivenDigit(int input1, int input2){
        for(int i = input1 - 1; i > 0; i--){
            if(!containsDigit(i, input2)){
                return i;
            }
        }
        return -1;
    }

    private static boolean containsDigit(int i, int input2) {
        while(i != 0){
            if(i % 10 == input2){
                return true;
            }
            i = i / 10;
        }
        return false;
    }

}

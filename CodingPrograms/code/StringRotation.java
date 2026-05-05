package programs;

public class StringRotation {

    public static String input1 = "apple";
    public static String input2 = "leapp";
    public static void main(String[] args) {

        if(findStringIsRotationOfAnotherString()){
            System.out.println(input2 + " is a rotation of " + input1);
        } else {
            System.out.println(input2 + " is NOT a rotation of " + input1);
        }
    }

    static boolean findStringIsRotationOfAnotherString(){

        if(input1.length() != input2.length()){
            return false;
        }
        String combinedInput = input1 + input1;
        return combinedInput.contains(input2);
    }

}

package programs;

public class ArrayEqualityCheck {

    static int[] array1 = { 1, 2, 3, 4, 5};
    static int[] array2 = { 1, 2, 3, 4, 5};

    public static void main(String[] args) {
        checkArrayEquality();
    }

    static void checkArrayEquality(){

        boolean arrayEquality = true;

        if(array1.length == array2.length){
            for(int i = 0; i < array1.length; i++){
                if (array1[i] != array2[i]) {
                    arrayEquality = false;
                    break;
                }
            }
        } else {
           arrayEquality = false;
        }
        if(arrayEquality) {
            System.out.println("Arrays are equal");
        }else {
            System.out.println("Arrays are not equal");
        }
    }
}

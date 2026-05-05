package programs;

public class RemoveVowels {

    public static String input = "Java Program of the Day";
    public static void main(String[] args) {
        removeVowels();
        removeVowelsUsingLoop();
    }

    static void removeVowels() {

        input = input.replaceAll("[AEIOUaeiou]","");
        System.out.println(input);
    }

    static void removeVowelsUsingLoop() {

        char[] inputCharArr = input.toCharArray();

        for(int i = 0; i < inputCharArr.length;  i++){

            if(inputCharArr[i] == 'A' || inputCharArr[i] == 'a'){
                inputCharArr[i] = '\0';
            }else if(inputCharArr[i] == 'E' || inputCharArr[i] == 'e'){
                inputCharArr[i] = '\0';
            }else if(inputCharArr[i] == 'I' || inputCharArr[i] == 'i'){
                inputCharArr[i] = '\0';
            }else if(inputCharArr[i] == 'O' || inputCharArr[i] == 'o'){
                inputCharArr[i] = '\0';
            }else if(inputCharArr[i] == 'U' || inputCharArr[i] == 'u'){
                inputCharArr[i] = '\0';
            }

        }
        System.out.println(new String(inputCharArr));

    }
}

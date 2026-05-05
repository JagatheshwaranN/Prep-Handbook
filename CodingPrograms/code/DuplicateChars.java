package programs;

import java.util.Arrays;
import java.util.HashMap;

public class DuplicateChars {

    public static String input = "Better butter";
    public static void main(String[] args) {
        findDuplicateCharsUsingHashMap();
        findDuplicateCharsUsingSorting();
    }

    static void findDuplicateCharsUsingHashMap() {

        char[] inputCharArr = input.replaceAll("\\s+", "").toCharArray();
        HashMap<Character, Integer> duplicateCharsStore = new HashMap<>();
        for(char ch : inputCharArr){

            if(duplicateCharsStore.containsKey(ch)){
                duplicateCharsStore.put(ch, duplicateCharsStore.get(ch)+1);
            } else {
                duplicateCharsStore.put(ch, 1);
            }
        }
        System.out.println(duplicateCharsStore);
        for( char ch : duplicateCharsStore.keySet()){
            if(duplicateCharsStore.get(ch) >= 1){
                System.out.println(ch+"="+duplicateCharsStore.get(ch));
            }
        }
    }

    static void findDuplicateCharsUsingSorting() {

        char[] inputCharArr = input.replaceAll("\\s+", "").toCharArray();
        Arrays.sort(inputCharArr);
        String sortedInput = new String(inputCharArr);
        int inputLen = sortedInput.length();
        System.out.println(sortedInput);
        for(int i = 0; i < inputLen - 1; i++){
            int count = 1;
            while(i < inputLen - 1 && sortedInput.charAt(i)==sortedInput.charAt(i+1)){
                count++;
                i++;
            }
            if(count >= 1) {
                System.out.println(sortedInput.charAt(i)+"="+count);
            }
        }
    }
}

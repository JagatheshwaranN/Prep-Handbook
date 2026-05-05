package programs;

import java.util.HashMap;
import java.util.HashSet;

public class DuplicateElementsInArray {

    public static int[] inputArray = { 2, 4, 5, 2, 5, 6, 7};
    public static void main(String[] args) {
        findDuplicatesInArray();
        findDuplicatesInArrayUsingHashMap();
        findDuplicatesInArrayUsingHashSet();
    }

    static void findDuplicatesInArray() {

        for(int i = 0; i < inputArray.length; i++){
            for(int j = i+1; j < inputArray.length; j++){
                if(inputArray[i] == inputArray[j]){
                    System.out.println(inputArray[i]);
                }
            }
        }
    }

    static void findDuplicatesInArrayUsingHashMap(){

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int number : inputArray){
            if(map.get(number) == null){
                map.put(number, 1);
            } else {
                map.put(number, map.get(number)+1);
            }
        }
        for(int num : map.keySet()){
            if(map.get(num) > 1){
                System.out.println(num+"="+map.get(num));
            }
        }
    }

    static void findDuplicatesInArrayUsingHashSet(){

        HashSet<Integer> duplicates = new HashSet<>();
        for(int number : inputArray){
            if(!duplicates.add(number)){
                System.out.println(number);
            }
        }
    }

}

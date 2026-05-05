package programs;

import java.util.HashSet;

public class Intersection_CommonElementsInArrays {

    public static String[] s1 = {"ONE", "TWO", "THREE", "FOUR", "FIVE", "FOUR"};

    public static String[] s2 = {"THREE", "FOUR", "FIVE", "SIX", "SEVEN"};

    public static void main(String[] args) {
        findCommonElements();
    }

    static void findCommonElements() {
        HashSet<String> set = new HashSet<>();
        for (String a1 : s1) {
            for (String a2 : s2) {
                if (a1.equals(a2)) {
                    set.add(a1);
                }
            }
        }
        System.out.println(set);
    }

}

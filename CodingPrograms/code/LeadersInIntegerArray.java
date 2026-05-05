package programs;

import java.util.ArrayList;
import java.util.List;

public class LeadersInIntegerArray {

    public static int[] input = {14, 9, 11, 7, 8, 5, 3};

    public static void main(String[] args) {
        findLeadersInArray();
        findLeadersInArrayUsingList();
    }

    static void findLeadersInArray() {

        if (input.length != 0) {
            int currentLeader = input[input.length - 1];
            System.out.print(currentLeader + " ");
            for (int i = input.length - 2; i >= 0; i--) {
                if (input[i] > currentLeader) {
                    System.out.print(input[i] + " ");
                    currentLeader = input[i];
                }
            }
            System.out.println();
        } else {
            System.out.println("Given array is empty");
        }
    }

    static void findLeadersInArrayUsingList() {

        List<Integer> leaders = new ArrayList<>();
        if (input.length != 0) {
            int currentLeader = input[input.length - 1];
            leaders.add(currentLeader);
            for (int i = input.length - 2; i >= 0; i--) {
                if (input[i] > currentLeader) {
                    currentLeader = input[i];
                    leaders.add(input[i]);
                }
            }
        } else {
            System.out.println("Given array is empty");
        }
        System.out.println(leaders);
    }

}

package programs;

public class SubArrayInGivenArray {

    public static void main(String[] args) {
        int[] input1 =  {12, 5, 31, 9, 21, 8};
        int input2 = 45;
        findSubArray(input1, input2);
    }

    static void findSubArray(int[] arr, int sum){
        int currentSum = 0;
        int start = 0;
        for(int i = 0; i < arr.length; i++){
            currentSum = currentSum + arr[i];
            while(currentSum > sum && start < i){
                currentSum = currentSum - arr[start];
                start++;
            }
            if(currentSum == sum){
                System.out.println("Found sub array between indexes "+ start +" and "+ (i));
                for(int j = start; j <= i; j++ ){
                    System.out.print(arr[j]+" ");
                }
            }
        }
    }

}

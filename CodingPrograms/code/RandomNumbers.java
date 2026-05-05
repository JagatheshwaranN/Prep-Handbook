package programs;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbers {

    public static void main(String[] args) {
        generateRandomNumberUsingRandom();
        System.out.println("====================================");
        generateRandomNumberUsingMathRandom();
        System.out.println("====================================");
        generateRandomNumberUsingThreadLocalRandom();
        System.out.println("====================================");
        generateRandomNumberInGivenRange();
    }

    static void generateRandomNumberUsingRandom() {

        Random random = new Random();
        for(int i = 0; i < 5; i++){
            System.out.println("Random Int with SIGN : "+ random.nextInt());
            System.out.println("Random Int without SIGN : "+ Math.abs(random.nextInt()));
            System.out.println("Random Double : "+ random.nextDouble());
        }
    }

    static void generateRandomNumberUsingMathRandom() {

        for(int i = 0; i < 5; i++){
            System.out.println("Random Double : "+ Math.random());
        }
    }

    static void generateRandomNumberUsingThreadLocalRandom() {

        for(int i = 0; i < 5; i++){
            System.out.println("Random Int with SIGN : "+ ThreadLocalRandom.current().nextInt());
            System.out.println("Random Int without SIGN : "+ Math.abs(ThreadLocalRandom.current().nextInt()));
            System.out.println("Random Double : "+ ThreadLocalRandom.current().nextDouble());
        }
    }

    static void generateRandomNumberInGivenRange() {

        System.out.println("Random numbers between 0 and 50 are as below");
        for(int i = 0; i < 5; i++){
            System.out.println(new Random().nextInt(50));
            System.out.println((int)(Math.random() * 50));
            System.out.println(ThreadLocalRandom.current().nextInt(0, 50));
        }
    }

}

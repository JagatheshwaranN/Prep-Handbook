package programs;

import java.text.DecimalFormat;

public class CountCharsDigitsOthersAndPercentage {

    public static String input = "Tiger Runs @ The Speed Of 100 km/hour.";

    public static void main(String[] args) {
        countCharsDigitsOthers();
    }

    static void countCharsDigitsOthers() {

        char[] inputCharArr = input.toCharArray();
        int totalChars = inputCharArr.length;
        int alphaCount = 0;
        int upperAlphaCount = 0;
        int lowerAlphaCount = 0;
        int digitCount = 0;
        int specialCharCount = 0;

        for (int i = 0; i < inputCharArr.length; i++) {

//            if (Character.isAlphabetic(inputCharArr[i])){
//                alphaCount++;
//            }
            if (Character.isUpperCase(inputCharArr[i])) {
                upperAlphaCount++;
            } else if (Character.isLowerCase(inputCharArr[i])) {
                lowerAlphaCount++;
            } else if (Character.isDigit(inputCharArr[i])) {
                digitCount++;
            } else {
                specialCharCount++;
            }

        }

        System.out.println("Alphabets Count : " + alphaCount);
        System.out.println("UpperCase Count : " + upperAlphaCount);
        System.out.println("LowerCase Count : " + lowerAlphaCount);
        System.out.println("Digits Count : " + digitCount);
        System.out.println("SpecialChar Count : " + specialCharCount);

        double alphaPercent = alphaCount * 100.0 / totalChars;
        double upperAlphaPercent = upperAlphaCount * 100.0 / totalChars;
        double lowerAlphaPercent = lowerAlphaCount * 100.0 / totalChars;
        double digitPercent = digitCount * 100.0 / totalChars;
        double specialCharPercent = specialCharCount * 100.0 / totalChars;

        DecimalFormat formatter = new DecimalFormat("##.##");
        System.out.println("Alphabets Percent : " + formatter.format(alphaPercent) + "%");
        System.out.println("UpperCase Percent : " + formatter.format(upperAlphaPercent) + "%");
        System.out.println("LowerCase Percent : " + formatter.format(lowerAlphaPercent) + "%");
        System.out.println("Digits Percent : " + formatter.format(digitPercent) + "%");
        System.out.println("SpecialChar Percent : " + formatter.format(specialCharPercent) + "%");
    }


}

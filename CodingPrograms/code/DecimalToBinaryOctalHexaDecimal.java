package programs;

public class DecimalToBinaryOctalHexaDecimal {

    public static int number = 50;

    public static void main(String[] args) {
        convertDecimalToBinary();
        convertDecimalToOctal();
        convertDecimalToHexa();
    }

    static void convertDecimalToBinary() {
        int input = number;
        String binary = "";
        int remainder;
        while (input != 0) {
            remainder = input % 2;
            binary = remainder + binary;
            input = input / 2;
        }
        System.out.println(binary);
    }

    static void convertDecimalToOctal() {
        int input = number;
        String octal = "";
        int remainder;
        while (input != 0) {
            remainder = input % 8;
            octal = remainder + octal;
            input = input / 8;
        }
        System.out.println(octal);
    }

    static void convertDecimalToHexa() {
        char[] hexaDecimals = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int input = number;
        String hexa = "";
        int remainder;
        while (input != 0) {
            remainder = input % 16;
            hexa = hexaDecimals[remainder] + hexa;
            input = input / 16;
        }
        System.out.println(hexa);
    }

}

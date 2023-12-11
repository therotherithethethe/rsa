import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("enter the message");
        int message = scanner.nextInt();
        rsaExample(message);
    }

    private static void rsaExample(int messageToWorkIn) {
        SecureRandom random = new SecureRandom();
        int bitLength = 4096; //приклад

        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);

        BigInteger n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.valueOf(3);
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.TWO);
        }

        BigInteger d = e.modInverse(phi);

        BigInteger message = BigInteger.valueOf(messageToWorkIn);
        System.out.println("Original Message: " + message);

        BigInteger encrypted = message.modPow(e, n);
        System.out.println("Encrypted Message: " + encrypted);

        // Decrypt the message
        BigInteger decrypted = encrypted.modPow(d, n);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
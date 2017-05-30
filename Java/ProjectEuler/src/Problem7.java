/**
 * 10001st prime
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem7 {
    public static void main(String[] args) {
        int numPrimes = 0;
        int prime = 2;
        while (numPrimes <= 10001) {
            if (isPrime(prime)) {
                //System.out.println(prime);
                numPrimes++;
            }
            if (numPrimes != 10001) {
                prime++;
            }
        }
        prime--;
        System.out.println("\n\n" + prime);
    }

    private static boolean isPrime(int prime) {
        int max = (int) Math.sqrt(prime);
        for (int i = 2; i <= max; i++) {
            //System.out.println("\t" + i);
            if (prime % i == 0) {
                return false;
            }
        }
        return true;
    }
}

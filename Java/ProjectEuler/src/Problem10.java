import java.util.HashSet;
import java.util.Set;

/**
 * Find the sum of all the primes below two million.
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem10 {
    public static void main(String[] args) {
        //System.out.println(isPrime(4));
        Set<Long> primes = new HashSet<>();
        long maxprime = 1;
        while (maxprime < 2000000) {
            long prime = maxprime;
            boolean found = false;
            while (!found && prime < 2000000) {
                prime++;
                found = isPrime(prime);
                if (found) {
                    //System.out.println(prime + " is prime");
                    maxprime = prime;
                    primes.add(prime);
                }
            }

            if (prime >= 2000000){
                System.out.println("done");
                break;
            }
        }
        long sum = 0;
        for (Long i : primes) {
            //System.out.println(i);
            sum += i;
        }
        System.out.println("\n\n" + sum);
    }

    private static boolean isPrime(long prime) {
        //System.out.println(prime);
        long max = (int) Math.sqrt(prime);
        for (long i = 2; i <= max; i++) {
            if (prime % i == 0) {
                return false;
            }
        }
        return true;
    }
}

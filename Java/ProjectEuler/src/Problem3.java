import java.util.PriorityQueue;

/**
 * What is the largest prime factor of the number 600851475143 ?
 * Created by Pulkit Gupta on 5/23/2017.
 */
public class Problem3 {

    private static PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> y-x);

    public static void main(String[] args) {
        double num = 600851475143.0;
        findMaxPrime(num);
        System.out.println(heap.poll());
    }

    private static void findMaxPrime(double num) {
        double max = Math.sqrt(num);
        for(int i = 1; i < max; i++) {
            if (num % i == 0.0) {
                heap.add(i);
            }
        }
        while (!isPrime(heap.peek())) {
            findMaxPrime(heap.poll());
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

import java.util.PriorityQueue;

/**
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem4 {
    public static void main(String[] args) {
        PriorityQueue<Integer> maxpq = new PriorityQueue<>((x, y) -> y-x);
        for (int i = 0; i < 999; i++) {
            for (int j = 0; j < 999; j++) {
                int product = i * j;
                String s = String.valueOf(product);
                if (isPalindromic(s)) {
                    maxpq.add(product);
                    //System.out.println(product);
                }
            }
        }
        System.out.println(maxpq.poll());
    }

    private static boolean isPalindromic(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (!(s.charAt(i) == s.charAt(s.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}

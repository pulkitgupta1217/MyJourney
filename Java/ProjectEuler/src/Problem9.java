/**
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem9 {
    public static void main(String[] args) {
        for (int a = 1; a < 1000; a++) {
            for (int b = 1; b < 1000 - a; b++) {
                int c = 1000 - a - b;
                if (c > 0) {
                    int sum = a * a + b * b;
                    int value = c * c;
                    if (sum == value) {
                        System.out.println((a * b * c));
                    }
                }
            }
        }
    }
}

/**
 * sum square difference of [1...100]
 *
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem6 {
    public static void main(String[] args) {
        int sumsquare = 0;
        int squaresum = 101 * 50;
        squaresum = squaresum * squaresum;
        for (int i = 1; i <= 100; i++) {
            sumsquare += (i * i);
        }
        System.out.println(squaresum - sumsquare);
    }
}

import java.util.LinkedList;
import java.util.List;

/**
 * By considering the terms in the Fibonacci sequence whose values do not exceed
 * four million, find the sum of the even-valued terms.
 * Created by Pulkit Gupta on 5/23/2017.
 */
public class Problem2 {
    public static void main(String[] args) {
        List<Integer> fibonacci = new LinkedList<>();
        int sum = 0;
        buildTable(fibonacci);
        for (Integer num : fibonacci) {
            if (num % 2 == 0) {
                sum += num;
            }
        }
        System.out.println(sum);
    }

    private static void buildTable(List<Integer> fibonacci) {
        int a = 0;
        int b = 1;
        while(a <= 4000000 && b <= 4000000) {
            /*if (a != 0) {
                fibonacci.add(a);
            }*/
            fibonacci.add(b);
            int c = a + b;
            a = b;
            b = c;
        }
    }
}

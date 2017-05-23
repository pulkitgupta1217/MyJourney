import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by Pulkit Gupta on 3/29/2017.
 */
public class RandomSquareSingleThread {
    public static void main(String[] args) {
        System.out.println("enter side: ");
        int s = (new Scanner(System.in)).nextInt();
        int[][] square = generate(s);
        if (square != null) {
            print(square);
        }
    }

    public static void print(int[][] square) {
        for (int r = 0; r < square.length; r++) {
            for (int c = 0; c < square[r].length; c++) {
                System.out.print(square[r][c] + ", ");
            }
            System.out.println();
        }
    }

    public static int[][] generate(int s) {
        int[][] square = new int[s][s];
        int sum = s * (s * s + 1) / 2;
        int max = s * s;
        Random rand = new Random();
        boolean found = false;

        int iterations = 0;
        while (!found) {
            System.out.println("iteration: " + (++iterations));

            found = true;
            LinkedList<Integer> numList = new LinkedList<Integer>();
            for (int i = 1; i <= max; i++) {
                numList.add(i);
            }
            int[] colSum = new int[s];
            for (int r = 0; r < square.length; r++) {
                int rowSum = 0;
                for (int c = 0; c < square[r].length; c++) {
                    square[r][c] = numList.remove(rand.nextInt(numList.size()));
                    rowSum += square[r][c];
                    colSum[c] += square[r][c];
                }
                if (rowSum > sum) {
                    //System.out.println("failed");
                    found = false;
                    break;
                }
                for (int i = 0; i < colSum.length; i++) {
                    if (colSum[i] > sum) {
                        //System.out.println("failed");
                        found = false;
                        r = square.length;
                        break;
                    }
                }
            }
            int asum = 0;
            int bsum = 0;
            for (int i = 0; i < square.length; i++) {
                asum += square[i][i];
                bsum += square[s - i - 1][s - i - 1];
                if (asum > sum || bsum > sum) {
                    //System.out.println("failed diag");
                    found = false;
                    break;
                }
            }
            if (asum < sum || bsum < sum) {
                //System.out.println("failed diag");
                found = false;
            }
/*            if (!found) {
                System.out.println("failed");
            }*/
        }
        return square;
    }
}

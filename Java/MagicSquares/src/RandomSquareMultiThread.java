import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Pulkit Gupta on 3/29/2017.
 */
public class RandomSquareMultiThread {
    private static int s;
    private static int sum;
    private static int max;
    private static Random rand = new Random();
    private static long iterations;

    public static void main(String[] args) {
        System.out.println("enter side: ");
        s = (new Scanner(System.in)).nextInt();
        System.out.println(new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
        sum = s * (s * s + 1) / 2;
        max = s * s;
        for (int i = 0; i < 2500; i++) {
            (new GenerateThread("t" + i)).start();
            //System.out.println(i);
        }
        System.out.println("made threads");
//        GenerateThread t1 = new GenerateThread("t1");
//        t1.start();
//        GenerateThread t2 = new GenerateThread("t2");
//        t2.start();
//        GenerateThread t3 = new GenerateThread("t3");
//        t3.start();
//        GenerateThread t4 = new GenerateThread("t4");
//        t4.start();
    }


    private static class GenerateThread extends Thread {
        private Thread t;
        private String threadName;

        GenerateThread( String name) {
            threadName = name;
            //System.out.println("Creating " +  threadName );
        }

        public void run() {
            //System.out.println("Running " +  threadName );
            int[][] square = new int[s][s];
            boolean found = false;
            while (!found) {
                iterations++;
                found = true;
                LinkedList<Integer> list = new LinkedList<Integer>();
                for (int i = 1; i <= max; i++) {
                    list.add(i);
                }
                int[] cSum = new int[s];
                int asum = 0;
                int bsum = 0;
                for (int r = 0; r < square.length; r++) {
                    int rSum = 0;
                    for (int c = 0; c < square[r].length; c++) {
                        square[r][c] = list.remove(rand.nextInt(list.size()));
                        rSum += square[r][c];
                        cSum[c] += square[r][c];
                    }
                    if (rSum != sum) {
                        found = false;
                        break;
                    }
                    asum += square[r][r];
                    bsum += square[s - r - 1][s - r - 1];
                    if (asum > sum || bsum > sum) {
                        found = false;
                        break;
                    }

                    for (int i = 0; i < cSum.length; i++) {
                        if (cSum[i] > sum) {
                            found = false;
                            r = square.length;
                            break;
                        }
                    }
                }

                if (asum != sum || bsum != sum) {
                    found = false;
                }
            }
            synchronized (System.out) {
                System.out.println(new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
                System.out.println("Thread " + threadName + " exiting." + iterations);
                System.out.println("stopping all threads");
                print(square);
                System.exit(100);
            }
        }

        public void start () {
            //System.out.println("Starting " +  threadName );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
        public void print(int[][] square) {
            for (int r = 0; r < square.length; r++) {
                for (int c = 0; c < square[r].length; c++) {
                    System.out.print(square[r][c] + ", ");
                }
                System.out.println();
            }
        }
    }
}

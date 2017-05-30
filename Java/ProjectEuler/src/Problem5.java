/**
 * find the lcm of [1...20]
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem5 {
    public static void main(String[] args) {
        int num = 6;
        boolean found = false;
        while (!found) {
            found = check(num);
            num+=1;
        }
        System.out.println(num - 1);

    }

    public static boolean check(int num) {
        for (int i = 1; i <= 20; i++) {
            if (num % i != 0) {
                return false;
            }
        }
        return true;
    }
}

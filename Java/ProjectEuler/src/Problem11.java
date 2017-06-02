import java.util.Arrays;
import java.util.Calendar;

/**
 * What is the greatest product of four adjacent numbers in the same direction
 * (up, down, left, right, or diagonally) in the 20×20 grid?
 * Created by Pulkit Gupta on 5/24/2017.
 */
public class Problem11 {
    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime());
        int[][] grid = new int[20][20];
        String[] r1 = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08".split(" ");
        String[] r2 = "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00".split(" ");
        String[] r3 = "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65".split(" ");
        String[] r4 = "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91".split(" ");
        String[] r5 = "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80".split(" ");
        String[] r6 = "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50".split(" ");
        String[] r7 = "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70".split(" ");
        String[] r8 = "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21".split(" ");
        String[] r9 = "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72".split(" ");
        String[] r10 = "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95".split(" ");
        String[] r11 = "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92".split(" ");
        String[] r12 = "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57".split(" ");
        String[] r13 = "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58".split(" ");
        String[] r14 = "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40".split(" ");
        String[] r15 = "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66".split(" ");
        String[] r16 = "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69".split(" ");
        String[] r17 = "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36".split(" ");
        String[] r18 = "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16".split(" ");
        String[] r19 = "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54".split(" ");
        String[] r20 = "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48".split(" ");
        String[][] sGrid = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11,  r12, r13, r14, r15, r16, r17, r18, r19, r20};

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = Integer.parseInt(sGrid[r][c]);
                System.out.print(grid[r][c] + ", ");
            }
            System.out.println();
        }
        System.out.println("built table");
        int max = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                //row
                int product = 1;
                for (int i = 0; i < 4 && c < grid[r].length - 3; i++) {
                    product *= grid[r][c + i];
                }
                max = Math.max(product, max);

                //col
                product = 1;
                for (int i  = 0; i < 4 && r < grid.length - 3; i++) {
                    product *= grid[r + i][c];
                }
                max = Math.max(product, max);

                //main diag
                product = 1;
                for (int i = 0; i < 4 && r < grid.length - 3 && c < grid[r].length - 3; i++) {
                    product *= grid[r + i][c + i];
                }
                max = Math.max(product, max);

                //counter diag
                product = 1;
                for (int i = 0; i < 4 && r > 3 && c > 3; i++) {
                    product *= grid[r - i][c - i];
                }
                max = Math.max(product, max);
                System.out.println(max);
            }
        }
        System.out.println("found max: " + max);

        /*for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length - 4; c++){
                int sum = grid[r][c] * grid[r][c + 1] * grid[r][c + 2] * grid[r][c + 3];
                max = Math.max(max, sum);
            }
        }
        int rowsum = max;
        System.out.println(rowsum);
        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length - 4; r++) {
                int sum = grid[r][c] * grid[r + 1][c] * grid[r + 2][c] * grid[r+3][c];
                max = Math.max(max, sum);
            }
        }
        int colsum = max;
        System.out.println(colsum);
        //TODO: fix
        for (int r = 0; r < grid.length - 4; r++) {
            for (int c = 0; c < grid[r].length - 4; c++) {
                int sum = grid[r][c] * grid[r][c + 1] * grid[r][c + 2] * grid[r][c + 3];
                max = Math.max(max, sum);
            }
        }
        for (int r = grid.length - 1; r >= 3; r--) {
            for (int c = grid[r].length - 1; c >= 3; c--) {
                int sum = grid[r][c] * grid[r][c - 1] * grid[r][c - 2] * grid[r][c - 3];
                max = Math.max(max, sum);
            }
        }
        int diagsum = max;
        System.out.println("\n" + max);*/
    }
}

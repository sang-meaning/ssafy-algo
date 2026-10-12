import java.util.Scanner;

public class Solution {

    static int N;
    static int count;
    static boolean[] col;
    static boolean[] left;
    static boolean[] right;

    static void backtracking(int row) {

        if(row == N) {
            count++;
            return;
        }

        for(int i=0; i<N; i++) {

            if(col[i]) {
                continue;
            }

            if(left[row+i]) {
                continue;
            }

            if(right[row-i+N-1]) {
                continue;
            }

            col[i] = true;
            left[row+i] = true;
            right[row-i+N-1] = true;

            backtracking(row+1);

            col[i] = false;
            left[row+i] = false;
            right[row-i+N-1] = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            N = sc.nextInt();

            count = 0;

            col = new boolean[N];
            left = new boolean[2*N-1];
            right = new boolean[2*N-1];

            backtracking(0);

            System.out.println("#" + tc + " " + count);
        }

        sc.close();
    }
}
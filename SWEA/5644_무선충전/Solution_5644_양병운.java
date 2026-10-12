import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};
	static int[][][] map;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int M = sc.nextInt();
            int A = sc.nextInt();
			map = new int[11][11][A];
            int[] moveA = new int[M + 1];
            int[] moveB = new int[M + 1];

            for (int i = 1; i <= M; i++) moveA[i] = sc.nextInt();
            for (int i = 1; i <= M; i++) moveB[i] = sc.nextInt();

            for (int bc = 0; bc < A; bc++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int C = sc.nextInt();
                int P = sc.nextInt();

                for (int r = 1; r <= 10; r++) {
                    for (int c = 1; c <= 10; c++) {
                        if (Math.abs(c - x) + Math.abs(r - y) <= C) {
                            map[r][c][bc] = P;
                        }
                    }
                }
            }

            int ax = 1;
            int ay = 1;
            int bx = 10;
            int by = 10;
            int answer = 0;

            for (int t = 0; t <= M; t++) {
                int max = 0;
                for (int i = 0; i < A; i++) {
                    for (int j = 0; j < A; j++) {
                        int aPower = map[ay][ax][i];
                        int bPower = map[by][bx][j];
                        int sum;
                        if (i == j) sum = Math.max(aPower, bPower);
                        else  sum = aPower + bPower;
                        max = Math.max(max, sum);
                    }
                }
                answer += max;
                if (t == M) break;
                ax += dx[moveA[t + 1]];
                ay += dy[moveA[t + 1]];
                bx += dx[moveB[t + 1]];
                by += dy[moveB[t + 1]];
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }
}
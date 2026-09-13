import java.util.Scanner;

class Solution {
    static int M, A;
    static int[][] bc;

    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            M = sc.nextInt();
            A = sc.nextInt();

            int[] moveA = new int[M];
            int[] moveB = new int[M];

            for (int i = 0; i < M; i++) {
                moveA[i] = sc.nextInt();
            }

            for (int i = 0; i < M; i++) {
                moveB[i] = sc.nextInt();
            }

            bc = new int[A][4];

            for (int i = 0; i < A; i++) {
                bc[i][0] = sc.nextInt();
                bc[i][1] = sc.nextInt();
                bc[i][2] = sc.nextInt();
                bc[i][3] = sc.nextInt();
            }

            int ax = 1;
            int ay = 1;

            int bx = 10;
            int by = 10;

            int answer = 0;

            answer += charge(ax, ay, bx, by);

            for (int time = 0; time < M; time++) {
                ax += dx[moveA[time]];
                ay += dy[moveA[time]];

                bx += dx[moveB[time]];
                by += dy[moveB[time]];

                answer += charge(ax, ay, bx, by);
            }

            System.out.println("#" + test_case + " " + answer);
        }

        sc.close();
    }

    static int charge(int ax, int ay, int bx, int by) {
        int max = 0;

        for (int i = -1; i < A; i++) {
            if (i != -1 && !canCharge(ax, ay, i)) {
                continue;
            }

            for (int j = -1; j < A; j++) {
                if (j != -1 && !canCharge(bx, by, j)) {
                    continue;
                }

                int sum = 0;

                if (i == -1 && j == -1) {
                    sum = 0;
                } else if (i == -1) {
                    sum = bc[j][3];
                } else if (j == -1) {
                    sum = bc[i][3];
                } else if (i == j) {
                    sum = bc[i][3];
                } else {
                    sum = bc[i][3] + bc[j][3];
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    static boolean canCharge(int x, int y, int index) {
        int distance = Math.abs(x - bc[index][0])
                + Math.abs(y - bc[index][1]);

        return distance <= bc[index][2];
    }
}
import java.util.*;

public class Solution_1494_유혜진 {
    static int N;
    static int[] x, y;
    static long totalX, totalY, answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            x = new int[N];
            y = new int[N];
            totalX = 0;
            totalY = 0;
            for (int i = 0; i < N; i++) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
                totalX += x[i];
                totalY += y[i];
            }

            answer = Long.MAX_VALUE;
            comb(0, 0, 0, 0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    static void comb(int idx, int cnt, long sx, long sy) {
        if (cnt == N / 2) {
            long vx = 2 * sx - totalX;
            long vy = 2 * sy - totalY;
            answer = Math.min(answer, vx * vx + vy * vy);
            return;
        }
        if (idx == N) return;
        if (N - idx < N / 2 - cnt) return; // 남은 지렁이로 N/2를 못 채우면 중단

        comb(idx + 1, cnt + 1, sx + x[idx], sy + y[idx]); // idx번 지렁이가 움직임
        comb(idx + 1, cnt, sx, sy);                       // idx번 지렁이는 가만히
    }
}
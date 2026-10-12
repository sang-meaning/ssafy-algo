import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] S;
    static boolean[] selected;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            S = new int[N][N];
            selected = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            selected[0] = true;
            comb(1, 1);

            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }

        System.out.print(sb);
    }

    static void comb(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        if (idx >= N) return;

        selected[idx] = true;
        comb(idx + 1, count + 1);

        selected[idx] = false;
        comb(idx + 1, count);
    }

    static void calculateDifference() {
        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (selected[i] && selected[j]) {
                    sumA += S[i][j] + S[j][i];
                } else if (!selected[i] && !selected[j]) {
                    sumB += S[i][j] + S[j][i];
                }
            }
        }

        int diff = Math.abs(sumA - sumB);
        minDiff = Math.min(minDiff, diff);
    }
}
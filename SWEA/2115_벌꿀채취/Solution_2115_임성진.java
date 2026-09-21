import java.io.*;

public class Solution_2115_임성진 {
    static int M, C;
    static int[] cells;
    static int best;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        in.nextToken(); int T = (int) in.nval;
        for (int tc = 1; tc <= T; tc++) {
            in.nextToken(); int N = (int) in.nval;
            in.nextToken(); M = (int) in.nval;
            in.nextToken(); C = (int) in.nval;

            int[][] honey = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    in.nextToken();
                    honey[i][j] = (int) in.nval;
                }

            // 1) 모든 (행, 시작열) 구간의 최대 이익을 미리 계산 (부분집합 완전탐색)
            int W = N - M + 1;
            int[][] profit = new int[N][W];
            cells = new int[M];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < W; j++) {
                    for (int t = 0; t < M; t++) cells[t] = honey[i][j + t];
                    best = 0;
                    dfs(0, 0, 0);
                    profit[i][j] = best;
                }

            // 2) 겹치지 않는 두 구간의 합 최대
            int answer = 0;
            for (int r1 = 0; r1 < N; r1++)
                for (int c1 = 0; c1 < W; c1++)
                    for (int r2 = r1; r2 < N; r2++) {
                        int from = (r1 == r2) ? c1 + M : 0;   // 같은 행이면 M칸 뒤부터
                        for (int c2 = from; c2 < W; c2++) {
                            int sum = profit[r1][c1] + profit[r2][c2];
                            if (sum > answer) answer = sum;
                        }
                    }
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }

    /** idx번째 벌통을 채취할지 말지 (sum: 채취량 합, score: 제곱합) */
    static void dfs(int idx, int sum, int score) {
        if (sum > C) return;
        if (idx == M) {
            if (score > best) best = score;
            return;
        }
        dfs(idx + 1, sum + cells[idx], score + cells[idx] * cells[idx]);
        dfs(idx + 1, sum, score);
    }
}
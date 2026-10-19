
import java.io.*;
import java.util.*;

public class Solution_1494_하상호 {

    static int N;
    static long[][] points;
    static long answer;

    static void dfs(int idx, int count, long sumX, long sumY) {

        // 모든 지렁이를 확인한 경우
        if (idx == N) {
            if (count == N / 2) {
                long result = sumX * sumX + sumY * sumY;
                answer = Math.min(answer, result);
            }
            return;
        }

        // + 그룹에 N/2마리를 초과할 수 없음
        if (count < N / 2) {
            dfs(idx + 1, count + 1,
                sumX + points[idx][0],
                sumY + points[idx][1]);
        }

        // - 그룹에 N/2마리를 초과할 수 없음
        if (idx - count < N / 2) {
            dfs(idx + 1, count,
                sumX - points[idx][0],
                sumY - points[idx][1]);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            points = new long[N][2];
            answer = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st =
                    new StringTokenizer(br.readLine());

                points[i][0] = Long.parseLong(st.nextToken());
                points[i][1] = Long.parseLong(st.nextToken());
            }

            // 첫 번째 지렁이를 + 그룹에 고정
            dfs(1, 1, points[0][0], points[0][1]);

            System.out.println("#" + tc + " " + answer);
        }
    }
}

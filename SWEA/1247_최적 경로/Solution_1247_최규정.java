import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int answer;

    static int[] x;
    static int[] y;

    static int[][] dist;
    static boolean[] visited;

    static void dfs(int depth, int current, int total) {

        if (total >= answer) {
            return;
        }

        if (depth == N) {
            int finalDistance = total + dist[current][N + 1];
            answer = Math.min(answer, finalDistance);
            return;
        }

        for (int next = 1; next <= N; next++) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;

            dfs(depth + 1,next,total + dist[current][next]);
            
            visited[next] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = fs.nextInt();

            x = new int[N + 2];
            y = new int[N + 2];
            dist = new int[N + 2][N + 2];
            visited = new boolean[N + 2];

            x[0] = fs.nextInt();
            y[0] = fs.nextInt();

            x[N + 1] = fs.nextInt();
            y[N + 1] = fs.nextInt();

            for (int i = 1; i <= N; i++) {
                x[i] = fs.nextInt();
                y[i] = fs.nextInt();
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    int distance = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);

                    dist[i][j] = distance;
                    dist[j][i] = distance;
                }
            }

            answer = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
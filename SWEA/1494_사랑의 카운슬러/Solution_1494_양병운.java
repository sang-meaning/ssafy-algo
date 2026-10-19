
import java.io.*;
import java.util.*;

class Solution {
    static class Worm {
        int x, y;

        public Worm(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static Worm[] worms;
    static boolean[] visited;
    static long min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            worms = new Worm[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                worms[i] = new Worm(x, y);
            }
            visited = new boolean[N];
            min = Long.MAX_VALUE;
            dfs(0, 0);
            System.out.println("#" + test_case + " " + min);
        }
    }

    static void dfs(int depth, int start) {
        if (depth == N / 2) {
            long x = 0;
            long y = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    x += worms[i].x;
                    y += worms[i].y;
                } else {
                    x -= worms[i].x;
                    y -= worms[i].y;
                }
            }
            min = Math.min(min, x * x + y * y);
            return;
        }
        for (int i = start; i < N; i++) {
            visited[i] = true;
            dfs(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}

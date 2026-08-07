```java
import java.util.*;
import java.io.*;

class Solution {

    static int[][] map;
    static boolean[][] visited;

    // 파이프별 연결 가능 방향
    static boolean[][] pipe = {
        {},
        {true, true, true, true},
        {false, true, false, true},
        {true, false, true, false},
        {false, true, true, false},
        {false, false, true, true},
        {true, false, false, true},
        {true, true, false, false}
    };

    static int[][] delta = {{0, -1},{-1, 0},{0, 1},{1, 0}};

    static int T, N, M, R, C, L;
    static int ans;

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String args[]) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (i == R && j == C) {
                        visited[i][j] = true;
                    } else {
                        visited[i][j] = false;
                    }
                }
            }

            ans = 1;

            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(R, C, 1));

            while (!q.isEmpty()) {

                Node cur = q.poll();

                if (cur.time == L) {
                    continue;
                }
				//델타배열로 탐색방향 둘러보기
                for (int d = 0; d < 4; d++) {

                    // 현재 파이프가 해당 방향으로 열려있지 않은 경우 skip
                    if (!pipe[map[cur.r][cur.c]][d]) {
                        continue;
                    }

                    int nr = cur.r + delta[d][0];
                    int nc = cur.c + delta[d][1];

                    // 범위를 벗어났거나, 터널이 없거나, 이미 방문한 경우 skip
                    if (!isIn(nr, nc)|| map[nr][nc] == 0 || visited[nr][nc]) {
                        continue;
                    }

                    // 방문하려고 하는 파이프가 현재 파이프 방향으로 열려있는가?
                    if (pipe[map[nr][nc]][(d + 2) % 4]) {
                        visited[nr][nc] = true;
                        q.offer(new Node(nr, nc, cur.time + 1));
                        ans++;
                    }
                }
            }

            System.out.printf("#%d %d\n", test_case, ans);
        }
    }

    public static class Node {

        int r;
        int c;
        int time;

        Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static boolean isIn(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }
}
```

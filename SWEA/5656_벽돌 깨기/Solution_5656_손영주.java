package submission;

import java.io.*;
import java.util.*;

public class Solution_5656_손영주 {

    static int N, W, H, answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            int total = 0;

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] != 0) total++;
                }
            }

            answer = total;
            dfs(0, map);

            sb.append("#").append(tc).append(" ")
              .append(answer).append("\n");
        }

        System.out.print(sb);
    }

    // 구슬을 떨어뜨릴 열을 선택
    static void dfs(int depth, int[][] map) {
        int remaining = countBricks(map);
        answer = Math.min(answer, remaining);

        if (answer == 0 || depth == N) {
            return;
        }

        for (int c = 0; c < W; c++) {
            int r = 0;

            // 해당 열에서 가장 위에 있는 벽돌 탐색
            while (r < H && map[r][c] == 0) {
                r++;
            }

            if (r == H) continue;

            int[][] copy = copyMap(map);

            // 벽돌 폭발 및 중력 적용
            explode(copy, r, c);
            gravity(copy);

            dfs(depth + 1, copy);
        }
    }

    // 연쇄 폭발 BFS
    static void explode(int[][] map, int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            int power = map[r][c];
            if (power == 0) continue;

            map[r][c] = 0;

            for (int d = 0; d < 4; d++) {
                for (int k = 1; k < power; k++) {
                    int nr = r + dr[d] * k;
                    int nc = c + dc[d] * k;

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        break;
                    }

                    if (map[nr][nc] != 0) {
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    // 폭발 후 벽돌을 아래로 떨어뜨리기
    static void gravity(int[][] map) {
        for (int c = 0; c < W; c++) {
            int bottom = H - 1;

            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    int value = map[r][c];
                    map[r][c] = 0;
                    map[bottom][c] = value;
                    bottom--;
                }
            }
        }
    }

    static int[][] copyMap(int[][] map) {
        int[][] copy = new int[H][W];

        for (int r = 0; r < H; r++) {
            copy[r] = map[r].clone();
        }

        return copy;
    }

    static int countBricks(int[][] map) {
        int count = 0;

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (map[r][c] != 0) count++;
            }
        }

        return count;
    }
}

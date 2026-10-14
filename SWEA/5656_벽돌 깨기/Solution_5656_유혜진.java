import java.util.*;

// SWEA 5656. 벽돌 깨기 - 중복순열(구슬 떨어뜨릴 열) + BFS 연쇄 폭발 + 중력
public class Solution_5656_유혜진 {
    static int N, W, H, answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++)
                for (int j = 0; j < W; j++)
                    map[i][j] = sc.nextInt();

            answer = Integer.MAX_VALUE;
            dfs(0, map);
            System.out.println("#" + tc + " " + answer);
        }
    }

    // cnt: 지금까지 떨어뜨린 구슬 수
    static void dfs(int cnt, int[][] map) {
        int remain = count(map);
        if (remain == 0) { answer = 0; return; }
        if (cnt == N) { answer = Math.min(answer, remain); return; }
        if (answer == 0) return;

        for (int c = 0; c < W; c++) {
            // 해당 열에서 가장 위에 있는 벽돌 찾기
            int r = 0;
            while (r < H && map[r][c] == 0) r++;
            if (r == H) continue; // 빈 열이면 의미 없음

            int[][] copy = copyMap(map);
            boom(copy, r, c);
            gravity(copy);
            dfs(cnt + 1, copy);
        }
    }

    // (r, c)부터 연쇄 폭발
    static void boom(int[][] map, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c, map[r][c]});
        map[r][c] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int power = cur[2];
            for (int d = 0; d < 4; d++) {
                int nr = cur[0], nc = cur[1];
                for (int k = 1; k < power; k++) {
                    nr += dr[d];
                    nc += dc[d];
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
                    if (map[nr][nc] > 1) q.offer(new int[]{nr, nc, map[nr][nc]});
                    map[nr][nc] = 0;
                }
            }
        }
    }

    // 빈 공간 아래로 벽돌 내리기
    static void gravity(int[][] map) {
        for (int c = 0; c < W; c++) {
            int write = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    int v = map[r][c];
                    map[r][c] = 0;
                    map[write--][c] = v;
                }
            }
        }
    }

    static int count(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[i][j] != 0) cnt++;
        return cnt;
    }

    static int[][] copyMap(int[][] map) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) copy[i] = map[i].clone();
        return copy;
    }
}
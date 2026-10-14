
import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, H;
    static int answer;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;

            dfs(0, map);

            sb.append("#").append(tc)
              .append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    // 1. 구슬을 떨어뜨릴 열 선택 (DFS)
    static void dfs(int depth, int[][] map) {

        int remain = countBricks(map);
        answer = Math.min(answer, remain);

        // 모든 구슬을 사용했거나 벽돌이 없는 경우
        if (depth == N || remain == 0) {
            return;
        }

        for (int c = 0; c < W; c++) {

            // 해당 열에서 가장 위에 있는 벽돌 찾기
            int r = 0;

            while (r < H && map[r][c] == 0) {
                r++;
            }

            // 해당 열에 벽돌이 없으면 건너뛰기
            if (r == H) {
                continue;
            }

            // 다른 경우의 수에 영향을 주지 않도록 복사
            int[][] copy = copyMap(map);

            // 연쇄 폭발
            explode(copy, r, c);

            // 중력 적용
            gravity(copy);

            // 다음 구슬 발사
            dfs(depth + 1, copy);
        }
    }

    // 2. BFS 연쇄 폭발
    static void explode(int[][] map, int r, int c) {

        Queue<int[]> queue = new ArrayDeque<>();

        // 행, 열, 폭발 범위
        queue.offer(new int[]{r, c, map[r][c]});
        map[r][c] = 0;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int cr = current[0];
            int cc = current[1];
            int power = current[2];

            // 상하좌우 4방향
            for (int d = 0; d < 4; d++) {

                // 벽돌 숫자 - 1 만큼 폭발
                for (int k = 1; k < power; k++) {

                    int nr = cr + dr[d] * k;
                    int nc = cc + dc[d] * k;

                    // 범위 밖이면 종료
                    if (nr < 0 || nr >= H ||
                        nc < 0 || nc >= W) {
                        break;
                    }

                    // 빈 공간이면 다음 칸 확인
                    if (map[nr][nc] == 0) {
                        continue;
                    }

                    // 연쇄 폭발할 벽돌 저장
                    queue.offer(new int[]{
                        nr, nc, map[nr][nc]
                    });

                    // 중복 폭발 방지
                    map[nr][nc] = 0;
                }
            }
        }
    }

    // 3. 중력 처리
    static void gravity(int[][] map) {

        for (int c = 0; c < W; c++) {

            int bottom = H - 1;

            // 아래에서 위로 확인
            for (int r = H - 1; r >= 0; r--) {

                if (map[r][c] != 0) {

                    int value = map[r][c];
                    map[r][c] = 0;

                    // 벽돌을 아래쪽으로 이동
                    map[bottom][c] = value;
                    bottom--;
                }
            }
        }
    }

    // 4. 배열 복사
    static int[][] copyMap(int[][] map) {

        int[][] copy = new int[H][W];

        for (int r = 0; r < H; r++) {
            copy[r] = map[r].clone();
        }

        return copy;
    }

    // 5. 남아 있는 벽돌 개수 확인
    static int countBricks(int[][] map) {

        int count = 0;

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {

                if (map[r][c] != 0) {
                    count++;
                }
            }
        }

        return count;
    }
}

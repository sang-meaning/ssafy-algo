import java.io.*;
import java.util.*;

public class Solution {

    static int N, W, H;
    static int answer;

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // depth: 사용한 구슬 개수
    static void dfs(int depth, int[][] map) {

        if (answer == 0) {
            return;
        }

        int remaining = countBricks(map);

        // 모든 벽돌을 없앴다면 종료
        if (remaining == 0) {
            answer = 0;
            return;
        }

        // 구슬을 모두 사용했다면 정답 갱신
        if (depth == N) {
            answer = Math.min(answer, remaining);
            return;
        }

        // 구슬을 떨어뜨릴 열 선택
        for (int c = 0; c < W; c++) {

            int r = 0;

            // 해당 열의 가장 위 벽돌 찾기
            while (r < H && map[r][c] == 0) {
                r++;
            }

            // 빈 열이면 생략
            if (r == H) {
                continue;
            }

            int[][] nextMap = copyMap(map);

            explode(nextMap, r, c);
            applyGravity(nextMap);

            dfs(depth + 1, nextMap);
        }
    }

    // BFS로 연쇄 폭발 처리
    static void explode(int[][] map, int startR, int startC) {

        Queue<int[]> queue = new ArrayDeque<>();

        // 행, 열, 폭발 범위 저장
        queue.offer(new int[] {
            startR, startC, map[startR][startC]
        });

        map[startR][startC] = 0;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int power = current[2];

            for (int d = 0; d < 4; d++) {

                for (int distance = 1; distance < power; distance++) {

                    int nr = r + dr[d] * distance;
                    int nc = c + dc[d] * distance;

                    // 지도 밖이면 해당 방향 종료
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        break;
                    }

                    // 빈칸이어도 폭발은 계속 진행
                    if (map[nr][nc] == 0) {
                        continue;
                    }

                    // 숫자를 저장한 뒤 벽돌 제거
                    queue.offer(new int[] {
                        nr, nc, map[nr][nc]
                    });

                    map[nr][nc] = 0;
                }
            }
        }
    }

    // 남은 벽돌을 아래로 이동
    static void applyGravity(int[][] map) {

        for (int c = 0; c < W; c++) {

            int writeRow = H - 1;

            for (int r = H - 1; r >= 0; r--) {

                if (map[r][c] != 0) {

                    int value = map[r][c];

                    map[r][c] = 0;
                    map[writeRow][c] = value;

                    writeRow--;
                }
            }
        }
    }

    // 지도 복사
    static int[][] copyMap(int[][] map) {

        int[][] copy = new int[H][W];

        for (int r = 0; r < H; r++) {
            copy[r] = map[r].clone();
        }

        return copy;
    }

    // 남은 벽돌 개수 세기
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

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

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

            answer = countBricks(map);

            dfs(0, map);

            sb.append("#").append(tc)
              .append(" ").append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}
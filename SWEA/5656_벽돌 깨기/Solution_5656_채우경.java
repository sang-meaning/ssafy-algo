import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N, W, H;
    static int min;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            int totalBricks = 0;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 0) totalBricks++;
                }
            }

            min = totalBricks; // 남은 벽돌의 최소 개수 초기화

            // 구슬 N번 쏘기 (중복 순열 탐색)
            dropMarble(0, map);

            System.out.println("#" + tc + " " + min);
        }
    }

    // N개의 구슬 위치를 결정하는 중복순열 백트래킹
    static void dropMarble(int count, int[][] currentMap) {
        int remain = countBricks(currentMap);
        
        // 가지치기: 이미 벽돌이 0개이거나 N번 모두 쏜 경우
        if (remain == 0) {
            min = 0;
            return;
        }
        if (count == N) {
            min = Math.min(min, remain);
            return;
        }

        // 각 열(col)마다 구슬을 쏴보는 백트래킹
        for (int col = 0; col < W; col++) {
            int row = 0;
            while (row < H && currentMap[row][col] == 0) {
                row++;
            }

            // 해당 열에 벽돌이 없는 경우 넘어감
            if (row == H) continue;

            // 맵 복사본 생성 (상태 복원용)
            int[][] nextMap = copyMap(currentMap);

            // 1. 벽돌 폭발 (BFS)
            boom(nextMap, row, col);

            // 2. 벽돌 밑으로 떨어뜨리기
            downBricks(nextMap);

            // 3. 다음 구슬 쏘기
            dropMarble(count + 1, nextMap);

            if (min == 0) return; // 최적화: 0이 되면 더 이상 탐색 불필요
        }
    }

    // BFS를 이용한 연쇄 폭발 처리
    static void boom(int[][] map, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        if (map[r][c] > 1) {
            q.add(new int[]{r, c, map[r][c]});
        }
        map[r][c] = 0; // 명중한 벽돌 파괴

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int range = cur[2];

            for (int d = 0; d < 4; d++) {
                int nr = curR;
                int nc = curC;

                for (int k = 1; k < range; k++) {
                    nr += dx[d];
                    nc += dy[d];

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;

                    if (map[nr][nc] > 0) {
                        if (map[nr][nc] > 1) {
                            q.add(new int[]{nr, nc, map[nr][nc]});
                        }
                        map[nr][nc] = 0; // 파괴
                    }
                }
            }
        }
    }

    // 빈 공간 채우기 (벽돌 아래로 떨어뜨리기)
    static void downBricks(int[][] map) {
        for (int c = 0; c < W; c++) {
            int targetRow = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    int temp = map[r][c];
                    map[r][c] = 0;
                    map[targetRow][c] = temp;
                    targetRow--;
                }
            }
        }
    }

    // 남은 벽돌 수 카운트
    static int countBricks(int[][] map) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) count++;
            }
        }
        return count;
    }

    // 맵 복사
    static int[][] copyMap(int[][] origin) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            System.arraycopy(origin[i], 0, copy[i], 0, W);
        }
        return copy;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_한석호 {
    static int N, W, H;
    static int minBlocks;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            int totalBlocks = 0;

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] > 0) {
                        totalBlocks++;
                    }
                }
            }

            minBlocks = totalBlocks;
            dfs(0, totalBlocks, map);

            sb.append("#").append(t).append(" ").append(minBlocks).append("\n");
        }
        System.out.print(sb);
    }

    // depth: 떨어뜨린 구슬 수, remain: 남은 벽돌 수
    static void dfs(int depth, int remain, int[][] map) {
        if (remain == 0) {
            minBlocks = 0;
            return;
        }

        if (depth == N) {
            minBlocks = Math.min(minBlocks, remain);
            return;
        }

        for (int col = 0; col < W; col++) {
            // 해당 열에서 가장 위에 있는 벽돌 찾기
            int topRow = -1;
            for (int r = 0; r < H; r++) {
                if (map[r][col] > 0) {
                    topRow = r;
                    break;
                }
            }

            // 부술 벽돌이 없는 열이면 스킵
            if (topRow == -1) continue;

            // 맵 복사
            int[][] nextMap = copyMap(map);

            // 벽돌 폭파 (연쇄)
            int broken = explode(topRow, col, nextMap);

            // 중력 적용
            dropBlocks(nextMap);

            dfs(depth + 1, remain - broken, nextMap);

            // 이미 남은 벽돌이 0이면 조기 종료
            if (minBlocks == 0) return;
        }
    }

    // BFS를 이용한 연쇄 폭발 처리
    static int explode(int startR, int startC, int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, map[startR][startC]});
        map[startR][startC] = 0;
        int brokenCount = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int power = cur[2];

            if (power <= 1) continue;

            for (int d = 0; d < 4; d++) {
                for (int p = 1; p < power; p++) {
                    int nr = r + dr[d] * p;
                    int nc = c + dc[d] * p;

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
                    if (map[nr][nc] == 0) continue;

                    if (map[nr][nc] > 1) {
                        queue.offer(new int[]{nr, nc, map[nr][nc]});
                    }
                    map[nr][nc] = 0;
                    brokenCount++;
                }
            }
        }
        return brokenCount;
    }

    // 빈 공간 메우기 (중력)
    static void dropBlocks(int[][] map) {
        for (int c = 0; c < W; c++) {
            int writeRow = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    if (writeRow != r) {
                        map[writeRow][c] = map[r][c];
                        map[r][c] = 0;
                    }
                    writeRow--;
                }
            }
        }
    }

    static int[][] copyMap(int[][] src) {
        int[][] dest = new int[H][W];
        for (int i = 0; i < H; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, W);
        }
        return dest;
    }
}
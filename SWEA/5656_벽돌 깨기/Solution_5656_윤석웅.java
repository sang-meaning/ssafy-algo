import java.io.*;
import java.util.*;

class Solution {
    static int N, W, H, ans;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];

            for(int r=0; r<H; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<W; c++) map[r][c] = Integer.parseInt(st.nextToken());
            }

            ans = count(map);
            dfs(0, map);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int depth, int[][] board) {
        if(ans == 0) return; // 더 줄일 수 없는 최적 상태

        if(depth == N) {
            ans = Math.min(ans, count(board));
            return;
        }

        boolean shot = false;

        // 이번 구슬을 떨어뜨릴 열을 하나씩 완전탐색
        for(int c=0; c<W; c++) {
            int r = 0;
            while(r < H && board[r][c] == 0) r++;

            if(r == H) continue;

            shot = true;

            // 분기마다 원본 맵을 보존하기 위해 복사본 사용
            int[][] next = copy(board);
            breakBricks(next, r, c);
            gravity(next);
            dfs(depth + 1, next);
        }

        if(!shot) ans = 0;
    }

    static void breakBricks(int[][] board, int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();

        int power = board[sr][sc];
        board[sr][sc] = 0;
        q.offer(new int[]{sr, sc, power});

        // 벽돌 숫자만큼 4방향으로 퍼뜨리며 연쇄 폭발
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int d=0; d<4; d++) {
                for(int k=1; k<cur[2]; k++) {
                    int nr = cur[0] + dr[d] * k;
                    int nc = cur[1] + dc[d] * k;

                    if(nr < 0 || nr >= H || nc < 0 || nc >= W)
                        break;

                    if(board[nr][nc] == 0)
                        continue;

                    int nextPower = board[nr][nc];
                    board[nr][nc] = 0;

                    if(nextPower > 1)
                        q.offer(new int[]{nr, nc, nextPower});
                }
            }
        }
    }

    static void gravity(int[][] board) {
        // 각 열의 벽돌을 아래부터 다시 채운다.
        for(int c=0; c<W; c++) {
            int write = H - 1;

            for(int r=H-1; r>=0; r--) {
                if(board[r][c] == 0) continue;

                int value = board[r][c];
                board[r][c] = 0;
                board[write--][c] = value;
            }
        }
    }

    static int[][] copy(int[][] src) {
        int[][] dst = new int[H][W];

        for(int r=0; r<H; r++)
            System.arraycopy(src[r], 0, dst[r], 0, W);

        return dst;
    }

    static int count(int[][] board) {
        int cnt = 0;

        for(int r=0; r<H; r++)
            for(int c=0; c<W; c++)
                if(board[r][c] != 0) cnt++;

        return cnt;
    }
}

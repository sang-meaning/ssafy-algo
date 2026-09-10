import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] board = new int[12][12];
    static int[] core_r = new int[12];
    static int[] core_c = new int[12];
    static int core_cnt;

    static int max_connected, min_wire_len;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int place_wire(int r, int c, int d) {
        int len = 0;
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (board[nr][nc] != 0) {
                while (len > 0) {
                    nr -= dr[d];
                    nc -= dc[d];
                    board[nr][nc] = 0;
                    len--;
                }
                return -1;
            }
            board[nr][nc] = 2;
            len++;
            nr += dr[d];
            nc += dc[d];
        }
        return len;
    }

    static void remove_wire(int r, int c, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (board[nr][nc] == 2) board[nr][nc] = 0;
            nr += dr[d];
            nc += dc[d];
        }
    }

    static void dfs(int idx, int connected, int wire_len) {
        if (connected + (core_cnt - idx) < max_connected) return;

        if (idx == core_cnt) {
            if (connected > max_connected) {
                max_connected = connected;
                min_wire_len = wire_len;
            } else if (connected == max_connected) {
                if (wire_len < min_wire_len) {
                    min_wire_len = wire_len;
                }
            }
            return;
        }

        int r = core_r[idx];
        int c = core_c[idx];

        for (int d = 0; d < 4; d++) {
            int len = place_wire(r, c, d);
            if (len != -1) {
                dfs(idx + 1, connected + 1, wire_len + len);
                remove_wire(r, c, d);
            }
        }
        dfs(idx + 1, connected, wire_len);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            core_cnt = 0;
            max_connected = 0;
            min_wire_len = 999999;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            core_r[core_cnt] = i;
                            core_c[core_cnt] = j;
                            core_cnt++;
                        }
                    }
                }
            }

            dfs(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(min_wire_len).append("\n");
        }
        System.out.print(sb);
    }
}
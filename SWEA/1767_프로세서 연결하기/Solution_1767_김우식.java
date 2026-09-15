package swea;

import java.util.*;
import java.io.*;

public class SWEA1767 {

    static int N;
    static int[][] maxi;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static ArrayList<int[]> cores;

    static int maxCore;
    static int minWire;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            maxi = new int[N][N];
            cores = new ArrayList<>();

            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            for (int r = 0; r < N; r++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {

                    maxi[r][c] = Integer.parseInt(st.nextToken());

                    if (maxi[r][c] == 1) {
                        if (r != 0 && r != N - 1 &&
                            c != 0 && c != N - 1) {

                            cores.add(new int[]{r, c});
                        }
                    }
                }
            }

            dfs(0, 0, 0);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(minWire)
              .append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int idx, int connected, int wireLength) {

        if (idx == cores.size()) {

            if (connected > maxCore) {
                maxCore = connected;
                minWire = wireLength;
            } else if (connected == maxCore) {
                minWire = Math.min(minWire, wireLength);
            }

            return;
        }

        int r = cores.get(idx)[0];
        int c = cores.get(idx)[1];

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            int cnt = 0;
            boolean possible = true;

            while (nr >= 0 && nr < N &&
                   nc >= 0 && nc < N) {

                if (maxi[nr][nc] != 0) {
                    possible = false;
                    break;
                }

                cnt++;

                nr += dr[d];
                nc += dc[d];
            }

            if (!possible) {
                continue;
            }

            nr = r + dr[d];
            nc = c + dc[d];

            while (nr >= 0 && nr < N &&
                   nc >= 0 && nc < N) {

                maxi[nr][nc] = 2;

                nr += dr[d];
                nc += dc[d];
            }

            dfs(idx + 1, connected + 1, wireLength + cnt);

            nr = r + dr[d];
            nc = c + dc[d];

            while (nr >= 0 && nr < N &&
                   nc >= 0 && nc < N) {

                maxi[nr][nc] = 0;

                nr += dr[d];
                nc += dc[d];
            }
        }

        dfs(idx + 1, connected, wireLength);
    }
}
/* * 갈 수 있는 지 확인 * 전선 설치 * 다음 코어 확인 * 원상복구 * 현재 코어 연결 불가 일때 */
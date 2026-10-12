package swea;

import java.io.*;
import java.util.*;

public class Solution_1767_하상호 {

    static int N;
    static int[][] map;

    static List<Core> cores;

    static int maxConnected;
    static int minWire;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Core {
        int r;
        int c;

        Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            cores = new ArrayList<>();

            for (int r = 0; r < N; r++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {

                    map[r][c] = Integer.parseInt(st.nextToken());

                    if (map[r][c] == 1) {

                        // 가장자리 코어는 이미 연결됨
                        if (r == 0 || r == N - 1 || c == 0 || c == N - 1) {
                            continue;
                        }

                        cores.add(new Core(r, c));
                    }
                }
            }

            maxConnected = 0;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWire);
        }
    }

    static void dfs(int idx, int connected, int wireLength) {

        // 모든 코어를 확인한 경우
        if (idx == cores.size()) {

            if (connected > maxConnected) {

                maxConnected = connected;
                minWire = wireLength;

            } else if (connected == maxConnected) {

                minWire = Math.min(minWire, wireLength);
            }

            return;
        }

        // 가지치기
        // 현재 연결 수 + 남은 코어를 전부 연결해도
        // 기존 최대 연결 수보다 작다면 볼 필요 없음
        int remain = cores.size() - idx;

        if (connected + remain < maxConnected) {
            return;
        }

        Core core = cores.get(idx);

        // 4방향으로 전선 연결 시도
        for (int d = 0; d < 4; d++) {

            int length = getWireLength(core.r, core.c, d);

            // 연결 불가능
            if (length == -1) continue;

            // 전선 설치
            setWire(core.r, core.c, d, 2);

            dfs(idx + 1,
                connected + 1,
                wireLength + length);

            // 전선 제거
            setWire(core.r, core.c, d, 0);
        }

        // 이 코어를 연결하지 않는 경우
        dfs(idx + 1, connected, wireLength);
    }

    static int getWireLength(int r, int c, int dir) {

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        int length = 0;

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {

            // 다른 코어나 전선이 있는 경우
            if (map[nr][nc] != 0) {
                return -1;
            }

            length++;

            nr += dr[dir];
            nc += dc[dir];
        }

        return length;
    }

    static void setWire(int r, int c, int dir, int value) {

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {

            map[nr][nc] = value;

            nr += dr[dir];
            nc += dc[dir];
        }
    }
}
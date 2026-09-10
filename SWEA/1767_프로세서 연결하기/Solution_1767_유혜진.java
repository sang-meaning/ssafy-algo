import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_유혜진 {
    static int N, maxCore, minWire;
    static int[][] map;
    static ArrayList<Core> cores;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Core {
        int r, c;
        Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 이미 테두리에 있는 코어는 전선이 필요 없으므로 내부 코어만 리스트에 담음
                    if (map[i][j] == 1 && i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                        cores.add(new Core(i, j));
                    }
                }
            }

            maxCore = -1;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + t + " " + minWire);
        }
    }

    static void dfs(int idx, int coreCount, int wireLength) {
        // 모든 코어에 대한 탐색이 끝난 경우
        if (idx == cores.size()) {
            if (coreCount > maxCore) {
                maxCore = coreCount;
                minWire = wireLength;
            } else if (coreCount == maxCore) {
                minWire = Math.min(minWire, wireLength);
            }
            return;
        }

        // 가지치기: 남은 코어를 모두 연결해도 현재 최대 코어 수보다 적다면 탐색 중단
        if (coreCount + (cores.size() - idx) < maxCore) return;

        Core cur = cores.get(idx);

        // 1. 현재 코어를 연결하지 않는 경우
        dfs(idx + 1, coreCount, wireLength);

        // 2. 4방향(상, 하, 좌, 우)으로 전선을 놓아볼 수 있는지 확인하고 시도
        for (int d = 0; d < 4; d++) {
            int len = getLength(cur.r, cur.c, d);
            if (len > 0) {
                setWire(cur.r, cur.c, d, 2); // 전선 설치 (2로 표시)
                dfs(idx + 1, coreCount + 1, wireLength + len);
                setWire(cur.r, cur.c, d, 0); // 백트래킹 (원상복구)
            }
        }
    }

    // 해당 방향으로 전선을 놓을 수 있는지 확인하고 길이를 반환 (못 놓으면 0 반환)
    static int getLength(int r, int c, int d) {
        int nr = r, nc = c;
        int len = 0;
        while (true) {
            nr += dr[d];
            nc += dc[d];
            // 테두리에 도달하면 성공
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            // 가는 길에 다른 코어(1)나 기존 전선(2)이 있으면 실패
            if (map[nr][nc] != 0) return 0;
            len++;
        }
        return len;
    }

    // 전선을 설치하거나 제거하는 함수
    static void setWire(int r, int c, int d, int state) {
        int nr = r, nc = c;
        while (true) {
            nr += dr[d];
            nc += dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            map[nr][nc] = state;
        }
    }
}
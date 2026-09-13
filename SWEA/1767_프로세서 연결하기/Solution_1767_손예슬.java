import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] board;
    static int[][] core; // 코어 좌표 (r, c)
    static int coreCnt;
    static int maxCnt;   // 최대 연결된 코어 수
    static int minPower; // 그때의 최소 전력
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            List<int[]> cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    // 가장자리에 붙은 코어는 이미 연결된 것으로 취급(전력 0)해 탐색 대상에서 제외
                    if (board[i][j] == 1 && !(i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
                        cores.add(new int[]{i, j});
                    }
                }
            }

            coreCnt = cores.size();
            core = cores.toArray(new int[0][]);
            maxCnt = 0;
            minPower = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append(String.format("#%d %d%n", tc, minPower));
        }
        System.out.print(sb);
    }

    // idx: 현재 처리 중인 코어 인덱스, connectedCnt: 지금까지 연결한 코어 수, power: 지금까지 쓴 전력
    static void dfs(int idx, int connectedCnt, int power) {
        // 가지치기: 남은 코어를 전부 연결해도 현재 최대값을 못 넘으면 중단
        if (connectedCnt + (coreCnt - idx) < maxCnt) return;

        if (idx == coreCnt) {
            if (connectedCnt > maxCnt) {
                maxCnt = connectedCnt;
                minPower = power;
            } else if (connectedCnt == maxCnt && power < minPower) {
                minPower = power;
            }
            return;
        }

        int r = core[idx][0], cCol = core[idx][1];

        // 1) 4방향 시도
        for (int d = 0; d < 4; d++) {
            List<int[]> path = new ArrayList<>();
            int nr = r, nc = cCol;
            boolean ok = true;

            while (true) {
                nr += dr[d];
                nc += dc[d];
                // 보드 경계를 벗어나면 그 직전까지가 유효한 배선
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                if (board[nr][nc] != 0) { ok = false; break; }
                path.add(new int[]{nr, nc});
            }

            if (ok && !path.isEmpty()) {
                // 배선 표시
                for (int[] p : path) board[p[0]][p[1]] = 2;
                dfs(idx + 1, connectedCnt + 1, power + path.size());
                // 원상복구 (백트래킹)
                for (int[] p : path) board[p[0]][p[1]] = 0;
            }
        }

        // 2) 이 코어는 연결하지 않는 경우
        dfs(idx + 1, connectedCnt, power);
    }
}
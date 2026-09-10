import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_한석호 {

    static int N;
    static int[][] board;
    static List<int[]> cores;
    static int maxConnected;
    static int minWireLength;

    // 상, 하, 좌, 우
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            cores = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                    // 가장자리가 아닌 내부에 위치한 코어만 저장
                    if (board[r][c] == 1 && r > 0 && r < N - 1 && c > 0 && c < N - 1) {
                        cores.add(new int[]{r, c});
                    }
                }
            }

            maxConnected = -1;
            minWireLength = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minWireLength).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int idx, int connectedCount, int wireLength) {
        // 가지치기: 남은 코어를 전부 연결해도 현재 최대 연결 수보다 적으면 탐색 중단
        if (connectedCount + (cores.size() - idx) < maxConnected) {
            return;
        }

        // 기저 조건: 모든 코어 탐색 완료
        if (idx == cores.size()) {
            if (connectedCount > maxConnected) {
                maxConnected = connectedCount;
                minWireLength = wireLength;
            } else if (connectedCount == maxConnected) {
                minWireLength = Math.min(minWireLength, wireLength);
            }
            return;
        }

        int[] core = cores.get(idx);
        int r = core[0];
        int c = core[1];

        // 1. 4방향으로 전선 설치 시도
        for (int d = 0; d < 4; d++) {
            int wLen = checkWire(r, c, d);
            if (wLen > 0) {
                setWire(r, c, d, 2); // 전선 설치
                dfs(idx + 1, connectedCount + 1, wireLength + wLen);
                setWire(r, c, d, 0); // 백트래킹 (원상 복구)
            }
        }

        // 2. 현재 코어를 연결하지 않고 다음 코어로 넘어가는 경우
        dfs(idx + 1, connectedCount, wireLength);
    }

    // 전선을 연결할 수 있는지 확인하고, 가능하면 전선 길이 반환 (불가능하면 0)
    static int checkWire(int r, int c, int d) {
        int nr = r + DR[d];
        int nc = c + DC[d];
        int len = 0;

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (board[nr][nc] != 0) {
                return 0; // 다른 코어나 전선이 가로막고 있음
            }
            nr += DR[d];
            nc += DC[d];
            len++;
        }
        return len;
    }

    // 특정 방향으로 전선 설치(val = 2) 또는 제거(val = 0)
    static void setWire(int r, int c, int d, int val) {
        int nr = r + DR[d];
        int nc = c + DC[d];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            board[nr][nc] = val;
            nr += DR[d];
            nc += DC[d];
        }
    }
}
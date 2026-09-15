import java.io.*;
import java.util.*;

public class Solution_1767_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] map;
    static List<int[]> cores;
    static int maxCore, minWire;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            cores.add(new int[]{i, j});
                        }
                    }
                }
            }

            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWire);
        }
    }

    static void dfs(int idx, int coreCount, int wireLength) {
        if (coreCount + (cores.size() - idx) < maxCore) {
            return;
        }

        if (idx == cores.size()) {
            if (coreCount > maxCore) {
                maxCore = coreCount;
                minWire = wireLength;
            } else if (coreCount == maxCore) {
                minWire = Math.min(minWire, wireLength);
            }
            return;
        }

        int[] cur = cores.get(idx);
        int r = cur[0];
        int c = cur[1];

        for (int d = 0; d < 4; d++) {
            if (canConnect(r, c, d)) {
                int len = setWire(r, c, d, 2);
                dfs(idx + 1, coreCount + 1, wireLength + len);
                setWire(r, c, d, 0);
            }
        }

        dfs(idx + 1, coreCount, wireLength);
    }

    static boolean canConnect(int r, int c, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (map[nr][nc] != 0) {
                return false;
            }
            nr += dr[d];
            nc += dc[d];
        }
        return true;
    }

    static int setWire(int r, int c, int d, int val) {
        int count = 0;
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            map[nr][nc] = val;
            count++;
            nr += dr[d];
            nc += dc[d];
        }
        return count;
    }
}
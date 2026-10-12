import java.io.*;
import java.util.*;

public class 프로세스연결하기 {

    static int N;
    static int[][] map;
    static List<int[]> cores;
    static int maxConnected;
    static int minLength;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());

                    if (map[r][c] == 1 &&
                            r != 0 && r != N - 1 &&
                            c != 0 && c != N - 1) {
                        cores.add(new int[]{r, c});
                    }
                }
            }

            maxConnected = 0;
            minLength = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minLength);
        }
    }

    static void dfs(int index, int connected, int length) {
        if (connected + (cores.size() - index) < maxConnected) {
            return;
        }

        if (index == cores.size()) {
            if (connected > maxConnected) {
                maxConnected = connected;
                minLength = length;
            } else if (connected == maxConnected) {
                minLength = Math.min(minLength, length);
            }
            return;
        }

        int r = cores.get(index)[0];
        int c = cores.get(index)[1];

        for (int d = 0; d < 4; d++) {
            int wireLength = check(r, c, d);

            if (wireLength == -1) {
                continue;
            }

            connect(r, c, d, 2);
            dfs(index + 1, connected + 1, length + wireLength);
            connect(r, c, d, 0);
        }

        dfs(index + 1, connected, length);
    }

    static int check(int r, int c, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        int length = 0;

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (map[nr][nc] != 0) {
                return -1;
            }

            length++;
            nr += dr[d];
            nc += dc[d];
        }

        return length;
    }

    static void connect(int r, int c, int d, int value) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            map[nr][nc] = value;
            nr += dr[d];
            nc += dc[d];
        }
    }
}

import java.util.Scanner;

public class Solution {
    static int N;
    static int[][] map;
    static int[][] cores;
    static int coreCount;
    static int maxConnected, minLength;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();

            map = new int[N][N];
            cores = new int[N * N][2];
            coreCount = 0;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    map[x][y] = sc.nextInt();

                    if (map[x][y] == 1
                            && x > 0 && x < N - 1
                            && y > 0 && y < N - 1) {
                        cores[coreCount][0] = x;
                        cores[coreCount][1] = y;
                        coreCount++;
                    }
                }
            }

            maxConnected = -1;
            minLength = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minLength);
        }

        sc.close();
    }

    static void dfs(int index, int connectedCount, int wireLength) {
        if (connectedCount + coreCount - index < maxConnected) {
            return;
        }

        if (index == coreCount) {
            if (connectedCount > maxConnected) {
                maxConnected = connectedCount;
                minLength = wireLength;
            } else if (connectedCount == maxConnected) {
                minLength = Math.min(minLength, wireLength);
            }
            return;
        }

        int x = cores[index][0];
        int y = cores[index][1];

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            int length = 0;
            boolean possible = true;

            while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] != 0) {
                    possible = false;
                    break;
                }

                length++;
                nx += dx[d];
                ny += dy[d];
            }

            if (!possible) {
                continue;
            }

            nx = x + dx[d];
            ny = y + dy[d];

            for (int i = 0; i < length; i++) {
                map[nx][ny] = 2;
                nx += dx[d];
                ny += dy[d];
            }

            dfs(index + 1, connectedCount + 1, wireLength + length);

            nx = x + dx[d];
            ny = y + dy[d];

            for (int i = 0; i < length; i++) {
                map[nx][ny] = 0;
                nx += dx[d];
                ny += dy[d];
            }
        }

        dfs(index + 1, connectedCount, wireLength);
    }
}
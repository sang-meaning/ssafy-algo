import java.io.*;
import java.util.*;

public class Solution_5648_정서우 {

    static class Atom {
        int x, y, dir, energy;

        Atom(int x, y, dir, energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

    // 0: 상(y+), 1: 하(y-), 2: 좌(x-), 3: 우(x+)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map = new int[4001][4001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            Queue<Atom> q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                q.offer(new Atom(x, y, dir, energy));
                map[x][y] = energy;
            }

            int totalEnergy = 0;

            while (!q.isEmpty()) {
                Atom cur = q.poll();

                if (map[cur.x][cur.y] > cur.energy) {
                    totalEnergy += map[cur.x][cur.y];
                    map[cur.x][cur.y] = 0;
                    continue;
                }

                map[cur.x][cur.y] = 0;

                int nx = cur.x + dx[cur.dir];
                int ny = cur.y + dy[cur.dir];

                if (nx < 0 || nx > 4000 || ny < 0 || ny > 4000) {
                    continue;
                }

                map[nx][ny] += cur.energy;
                q.offer(new Atom(nx, ny, cur.dir, cur.energy));
            }

            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }

        System.out.print(sb);
    }
}
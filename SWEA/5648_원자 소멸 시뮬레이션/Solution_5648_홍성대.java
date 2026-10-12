import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static class Atom {
        int x, y, dir, energy;
        boolean dead;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
            this.dead = false;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map = new int[4005][4005];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; ++tc) {
            int n = Integer.parseInt(br.readLine().trim());
            Atom[] atoms = new Atom[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                x = (x + 1000) * 2;
                y = (y + 1000) * 2;
                atoms[i] = new Atom(x, y, dir, energy);
            }

            int totalEnergy = 0;

            for (int t = 0; t <= 4000; t++) {
                for (int i = 0; i < n; i++) {
                    if (atoms[i].dead) continue;

                    atoms[i].x += dx[atoms[i].dir];
                    atoms[i].y += dy[atoms[i].dir];

                    if (atoms[i].x < 0 || atoms[i].x > 4000 || atoms[i].y < 0 || atoms[i].y > 4000) {
                        atoms[i].dead = true;
                        continue;
                    }

                    map[atoms[i].x][atoms[i].y] += atoms[i].energy;
                }

                for (int i = 0; i < n; i++) {
                    if (atoms[i].dead) continue;

                    if (map[atoms[i].x][atoms[i].y] > atoms[i].energy) {
                        totalEnergy += atoms[i].energy;
                        atoms[i].dead = true;
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (atoms[i].x >= 0 && atoms[i].x <= 4000 && atoms[i].y >= 0 && atoms[i].y <= 4000) {
                        map[atoms[i].x][atoms[i].y] = 0;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }

        System.out.print(sb);
    }
}
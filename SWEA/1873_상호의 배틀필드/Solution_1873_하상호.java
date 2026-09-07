package swea;

import java.io.*;
import java.util.*;

public class Solution_1873_하상호 {

    static int H, W;
    static char[][] map;

    static int tankR;
    static int tankC;

    static int dir;

    // 0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static char[] tankShape = {'^', '>', 'v', '<'};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            for (int r = 0; r < H; r++) {
                String line = br.readLine();

                for (int c = 0; c < W; c++) {
                    map[r][c] = line.charAt(c);

                    if (map[r][c] == '^') {
                        tankR = r;
                        tankC = c;
                        dir = 0;
                    } else if (map[r][c] == '>') {
                        tankR = r;
                        tankC = c;
                        dir = 1;
                    } else if (map[r][c] == 'v') {
                        tankR = r;
                        tankC = c;
                        dir = 2;
                    } else if (map[r][c] == '<') {
                        tankR = r;
                        tankC = c;
                        dir = 3;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String commands = br.readLine();

            for (int i = 0; i < N; i++) {
                char command = commands.charAt(i);

                if (command == 'U') {
                    move(0);
                } else if (command == 'R') {
                    move(1);
                } else if (command == 'D') {
                    move(2);
                } else if (command == 'L') {
                    move(3);
                } else if (command == 'S') {
                    shoot();
                }
            }

            System.out.print("#" + tc + " ");

            for (int r = 0; r < H; r++) {
                System.out.println(new String(map[r]));
            }
        }
    }

    static void move(int newDir) {

        // 먼저 방향 변경
        dir = newDir;
        map[tankR][tankC] = tankShape[dir];

        int nr = tankR + dr[dir];
        int nc = tankC + dc[dir];

        // 맵 밖이면 이동 불가
        if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
            return;
        }

        // 평지일 때만 이동
        if (map[nr][nc] == '.') {

            map[tankR][tankC] = '.';

            tankR = nr;
            tankC = nc;

            map[tankR][tankC] = tankShape[dir];
        }
    }

    static void shoot() {

        int nr = tankR;
        int nc = tankC;

        while (true) {

            nr += dr[dir];
            nc += dc[dir];

            // 맵 밖으로 나감
            if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                return;
            }

            // 강철 벽은 포탄 소멸
            if (map[nr][nc] == '#') {
                return;
            }

            // 벽돌 벽은 파괴 후 포탄 소멸
            if (map[nr][nc] == '*') {
                map[nr][nc] = '.';
                return;
            }
        }
    }
}

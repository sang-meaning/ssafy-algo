package org.example;

import java.util.*;
import java.io.*;

public class swea1873 {
    static int H, W;
    static char[][] map;
    static int tankR, tankC;
    static int dir;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[] tankShape = {'^', 'v', '<', '>'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            for (int i = 0; i < H; i++) {
                String s = br.readLine();

                for (int j = 0; j < W; j++) {
                    map[i][j] = s.charAt(j);

                    if (map[i][j] == '^') {
                        tankR = i;
                        tankC = j;
                        dir = 0;
                    } else if (map[i][j] == 'v') {
                        tankR = i;
                        tankC = j;
                        dir = 1;
                    } else if (map[i][j] == '<') {
                        tankR = i;
                        tankC = j;
                        dir = 2;
                    } else if (map[i][j] == '>') {
                        tankR = i;
                        tankC = j;
                        dir = 3;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String command = br.readLine();

            for (int i = 0; i < N; i++) {

                char cmd = command.charAt(i);

                if (cmd == 'U') {
                    move(0);

                } else if (cmd == 'D') {
                    move(1);

                } else if (cmd == 'L') {
                    move(2);

                } else if (cmd == 'R') {
                    move(3);

                } else if (cmd == 'S') {
                    shoot();
                }
            }

            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < H; i++) {
                sb.append(map[i]).append("\n");
            }
        }

        System.out.print(sb);
    }

    static void move(int newDir) {

        // 먼저 방향 변경
        dir = newDir;
        map[tankR][tankC] = tankShape[dir];

        int nr = tankR + dr[dir];
        int nc = tankC + dc[dir];

        // 맵 밖이면 이동 안 함
        if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
            return;
        }

        // 평지가 아니면 이동 안 함
        if (map[nr][nc] != '.') {
            return;
        }

        // 기존 전차 위치는 평지
        map[tankR][tankC] = '.';

        // 전차 위치 이동
        tankR = nr;
        tankC = nc;

        // 새 위치에 전차 표시
        map[tankR][tankC] = tankShape[dir];
    }

    static void shoot() {

        int nr = tankR + dr[dir];
        int nc = tankC + dc[dir];

        while (nr >= 0 && nr < H && nc >= 0 && nc < W) {

            // 벽돌 벽
            if (map[nr][nc] == '*') {
                map[nr][nc] = '.';
                return;
            }

            // 강철 벽
            if (map[nr][nc] == '#') {
                return;
            }

            // 계속 직진
            nr += dr[dir];
            nc += dc[dir];
        }
    }
}

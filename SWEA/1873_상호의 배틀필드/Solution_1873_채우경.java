import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // 상, 하, 좌, 우 순서 (U, D, L, R 매칭)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            char[][] map = new char[H][W];
            int r = 0, c = 0, dir = 0; // 전차의 위치(r, c)와 방향(dir)

            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                    // 전차 위치와 초기 방향 찾기
                    if (map[i][j] == '^') { r = i; c = j; dir = 0; }
                    else if (map[i][j] == 'v') { r = i; c = j; dir = 1; }
                    else if (map[i][j] == '<') { r = i; c = j; dir = 2; }
                    else if (map[i][j] == '>') { r = i; c = j; dir = 3; }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String cmds = br.readLine();

            // 명령어 처리
            for (int i = 0; i < N; i++) {
                char cmd = cmds.charAt(i);

                switch (cmd) {
                    case 'U':
                        dir = 0;
                        map[r][c] = '^';
                        if (r + dr[dir] >= 0 && r + dr[dir] < H && map[r + dr[dir]][c + dc[dir]] == '.') {
                            map[r][c] = '.'; // 원래 자리는 평지로
                            r += dr[dir];
                            c += dc[dir];
                            map[r][c] = '^';
                        }
                        break;
                    case 'D':
                        dir = 1;
                        map[r][c] = 'v';
                        if (r + dr[dir] >= 0 && r + dr[dir] < H && map[r + dr[dir]][c + dc[dir]] == '.') {
                            map[r][c] = '.';
                            r += dr[dir];
                            c += dc[dir];
                            map[r][c] = 'v';
                        }
                        break;
                    case 'L':
                        dir = 2;
                        map[r][c] = '<';
                        if (c + dc[dir] >= 0 && c + dc[dir] < W && map[r + dr[dir]][c + dc[dir]] == '.') {
                            map[r][c] = '.';
                            r += dr[dir];
                            c += dc[dir];
                            map[r][c] = '<';
                        }
                        break;
                    case 'R':
                        dir = 3;
                        map[r][c] = '>';
                        if (c + dc[dir] >= 0 && c + dc[dir] < W && map[r + dr[dir]][c + dc[dir]] == '.') {
                            map[r][c] = '.';
                            r += dr[dir];
                            c += dc[dir];
                            map[r][c] = '>';
                        }
                        break;
                    case 'S':
                        // 포탄 발사 로직
                        int pr = r;
                        int pc = c;
                        while (true) {
                            pr += dr[dir];
                            pc += dc[dir];

                            // 맵 범위를 벗어나거나 강철 벽(#)을 만나면 포탄 소멸
                            if (pr < 0 || pr >= H || pc < 0 || pc >= W || map[pr][pc] == '#') {
                                break;
                            }
                            // 벽돌 벽(*)을 만나면 벽을 파괴(`.`)하고 포탄 소멸
                            if (map[pr][pc] == '*') {
                                map[pr][pc] = '.';
                                break;
                            }
                        }
                        break;
                }
            }

            // 결과 출력
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
            System.out.print(sb.toString());
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
}
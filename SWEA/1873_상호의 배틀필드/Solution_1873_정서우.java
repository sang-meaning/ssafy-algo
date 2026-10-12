import java.io.*;
import java.util.*;

public class Solution_1873_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int H, W;
    static char[][] board;
    static char[] in;
    static int x, y, dir;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static char[] tank = { '^', 'v', '<', '>' };

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            board = new char[H][W];

            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < W; j++) {
                    board[i][j] = s.charAt(j);

                    if (board[i][j] == '^') {
                        x = i;
                        y = j;
                        dir = 0;
                        board[i][j] = '.';
                    } else if (board[i][j] == 'v') {
                        x = i;
                        y = j;
                        dir = 1;
                        board[i][j] = '.';
                    } else if (board[i][j] == '<') {
                        x = i;
                        y = j;
                        dir = 2;
                        board[i][j] = '.';
                    } else if (board[i][j] == '>') {
                        x = i;
                        y = j;
                        dir = 3;
                        board[i][j] = '.';
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            for (int i = 0; i < N; i++) {
                char ch = s.charAt(i);
                if (ch == 'S')
                    shoot();
                else
                    move(ch);
            }

            board[x][y] = tank[dir];

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < H; i++) {
                sb.append(board[i]).append('\n');
            }
        }

        System.out.println(sb);
    }

    static void move(char ch) {
        if (ch == 'U')
            dir = 0;
        else if (ch == 'D')
            dir = 1;
        else if (ch == 'L')
            dir = 2;
        else if (ch == 'R')
            dir = 3;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx >= 0 && ny >= 0 && nx < H && ny < W && board[nx][ny] == '.') {
            x = nx;
            y = ny;
        }
    }

    static void shoot() {
        int nx = x;
        int ny = y;

        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || ny < 0 || nx >= H || ny >= W)
                break;

            if (board[nx][ny] == '*') {
                board[nx][ny] = '.';
                break;
            }

            if (board[nx][ny] == '#')
                break;
        }
    }

}

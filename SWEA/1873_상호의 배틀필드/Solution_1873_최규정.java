import java.util.*;

public class Solution {
    static int height, width;
    static char[][] map;
    static int tankRow, tankCol, direction;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[] tankShape = {'^', 'v', '<', '>'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            height = sc.nextInt();
            width = sc.nextInt();
            map = new char[height][width];

            for (int r = 0; r < height; r++) {
                map[r] = sc.next().toCharArray();

                for (int c = 0; c < width; c++) {
                    for (int d = 0; d < 4; d++) {
                        if (map[r][c] == tankShape[d]) {
                            tankRow = r;
                            tankCol = c;
                            direction = d;
                        }
                    }
                }
            }

            int commandCount = sc.nextInt();
            String commands = sc.next();

            for (int i = 0; i < commandCount; i++) {
                char command = commands.charAt(i);

                switch (command) {
                    case 'U':
                        move(0);
                        break;
                    case 'D':
                        move(1);
                        break;
                    case 'L':
                        move(2);
                        break;
                    case 'R':
                        move(3);
                        break;
                    case 'S':
                        shoot();
                        break;
                }
            }

            System.out.print("#" + tc + " ");
            for (int r = 0; r < height; r++) {
                System.out.println(new String(map[r]));
            }
        }

        sc.close();
    }

    static void move(int nextDirection) {
        direction = nextDirection;
        map[tankRow][tankCol] = tankShape[direction];

        int nextRow = tankRow + dr[direction];
        int nextCol = tankCol + dc[direction];

        if (nextRow < 0 || nextRow >= height || nextCol < 0 || nextCol >= width) {
            return;
        }

        // 평지일 때만 이동
        if (map[nextRow][nextCol] == '.') {
            map[tankRow][tankCol] = '.';

            tankRow = nextRow;
            tankCol = nextCol;

            map[tankRow][tankCol] = tankShape[direction];
        }
    }

    static void shoot() {
        int bulletRow = tankRow + dr[direction];
        int bulletCol = tankCol + dc[direction];

        while (bulletRow >= 0 && bulletRow < height && bulletCol >= 0 && bulletCol < width) {
            if (map[bulletRow][bulletCol] == '*') {
                map[bulletRow][bulletCol] = '.';
                return;
            }

            if (map[bulletRow][bulletCol] == '#') {
                return;
            }

            bulletRow += dr[direction];
            bulletCol += dc[direction];
        }
    }
}
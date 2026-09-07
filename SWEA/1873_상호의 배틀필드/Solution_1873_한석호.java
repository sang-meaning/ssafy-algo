import java.util.Scanner;

public class Solution_1873_한석호 {
    static int H, W;
    static char[][] graph;
    static int tankX, tankY, direction;

    // 상, 하, 좌, 우 (0: ^, 1: v, 2: <, 3: >)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dirChar = {'^', 'v', '<', '>'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            H = sc.nextInt();
            W = sc.nextInt();

            graph = new char[H][W];
            tankX = 0;
            tankY = 0;
            direction = 0;

            for (int i = 0; i < H; i++) {
                String row = sc.next();
                for (int j = 0; j < W; j++) {
                    graph[i][j] = row.charAt(j);
                }
            }

            // 전차 위치 및 초기 방향 찾기
            boolean findTank = false;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    char c = graph[i][j];
                    if (c == '>' || c == '<' || c == '^' || c == 'v') {
                        tankX = i;
                        tankY = j;
                        findTank = true;

                        if (c == '^') direction = 0;
                        else if (c == 'v') direction = 1;
                        else if (c == '<') direction = 2;
                        else if (c == '>') direction = 3;

                        break;
                    }
                }
                if (findTank) break;
            }

            int c = sc.nextInt();
            String command = sc.next();

            // 명령어 처리
            for (int i = 0; i < c; i++) {
                char cmd = command.charAt(i);

                if (cmd == 'U') {
                    direction = 0;
                    move(direction);
                } else if (cmd == 'D') {
                    direction = 1;
                    move(direction);
                } else if (cmd == 'L') {
                    direction = 2;
                    move(direction);
                } else if (cmd == 'R') {
                    direction = 3;
                    move(direction);
                } else if (cmd == 'S') {
                    fire(tankX, tankY, direction);
                }
            }

            // 결과 출력
            System.out.print("#" + t + " ");
            for (int i = 0; i < H; i++) {
                System.out.println(new String(graph[i]));
            }
        }
        sc.close();
    }

    // 전차 이동 처리 함수
    static void move(int dir) {
        int nx = tankX + dx[dir];
        int ny = tankY + dy[dir];

        graph[tankX][tankY] = '.'; // 기존 위치는 평지로 바꿈

        // 범위 안이고 이동하려는 위치가 평지('.')라면 이동
        if (nx >= 0 && nx < H && ny >= 0 && ny < W && graph[nx][ny] == '.') {
            tankX = nx;
            tankY = ny;
        }

        // 새로운 위치(또는 제자리)에 변경된 방향의 전차를 배치
        graph[tankX][tankY] = dirChar[dir];
    }

    // 포탄 발사 처리 함수
    static void fire(int x, int y, int dir) {
        int currentX = x;
        int currentY = y;

        while (true) {
            int nx = currentX + dx[dir];
            int ny = currentY + dy[dir];

            if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                if (graph[nx][ny] == '*') { // 벽돌 벽을 만나면 평지로 바꾸고 소멸
                    graph[nx][ny] = '.';
                    break;
                } else if (graph[nx][ny] == '#') { // 강철 벽을 만나면 그냥 소멸
                    break;
                }
                currentX = nx;
                currentY = ny;
            } else { // 맵 밖으로 나가면 소멸
                break;
            }
        }
    }
}
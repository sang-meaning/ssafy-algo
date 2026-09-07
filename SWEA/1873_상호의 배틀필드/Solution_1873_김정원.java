package ssafy.swea.kjw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Tank {

    private int direction;
    private int x;
    private int y;

    public Tank(int direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Solution_1873_김정원 {

    static char[][] map;
    static Tank myTank;

    static int H;
    static int W;

    // 위, 오른쪽, 아래, 왼쪽
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static char[] tankDirections = {'^', '>', 'v', '<'};

    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            // 전장 생성
            for (int y = 0; y < H; y++) {
                String line = br.readLine();
                for (int x = 0; x < W; x++) {
                    map[y][x] = line.charAt(x);
                    if (isTank(map[y][x])) {
                        myTank = new Tank(getDirectionIndex(map[y][x]),x,y);
                    }
                }
            }
            int N = Integer.parseInt(br.readLine());
            String commands = br.readLine();
            // 명령 수행
            for (int i = 0; i < N; i++) {
                execute(commands.charAt(i));
            }
            System.out.print("#" + testCase + " ");
            print();
        }
    }

    static void execute(char command) {
        switch (command) {
            case 'U':
                move(0);
                break;
            case 'R':
                move(1);
                break;
            case 'D':
                move(2);
                break;
            case 'L':
                move(3);
                break;
            case 'S':
                shoot();
                break;
        }
    }

    static void move(int direction) {

        int currentX = myTank.getX();
        int currentY = myTank.getY();

        // 먼저 전차의 방향 변경
        myTank.setDirection(direction);
        map[currentY][currentX] = tankDirections[direction];

        int nextX = currentX + dx[direction];
        int nextY = currentY + dy[direction];

        // 이동할 위치가 맵 밖이면 이동하지 않음
        if (!isMapIn(nextX, nextY)) return;

        // 이동할 위치가 평지가 아니면 이동하지 않음
        if (map[nextY][nextX] != '.') return;      

        // 기존 전차 위치를 평지로 변경
        map[currentY][currentX] = '.';

        // 전차 위치 변경
        myTank.setX(nextX);
        myTank.setY(nextY);

        // 새로운 위치에 전차 표시
        map[nextY][nextX] = tankDirections[direction];
    }

    static void shoot() {
        int direction = myTank.getDirection();
        int bulletX = myTank.getX() + dx[direction];
        int bulletY = myTank.getY() + dy[direction];

        while (isMapIn(bulletX, bulletY)) {
            // 강철벽을 만나면 포탄 소멸
            if (map[bulletY][bulletX] == '#') return;

            // 벽돌벽을 만나면 벽을 파괴하고 포탄 소멸
            if (map[bulletY][bulletX] == '*') {
                map[bulletY][bulletX] = '.';
                return;
            }

            // 포탄 이동
            bulletX += dx[direction];
            bulletY += dy[direction];
        }
    }

    static boolean isMapIn(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    static boolean isTank(char field) {
        return field == '^' || field == '>' || field == 'v' || field == '<';
    }

    static int getDirectionIndex(char tankDirection) {
        switch (tankDirection) {
            case '^':
                return 0;
            case '>':
                return 1;
            case 'v':
                return 2;
            case '<':
                return 3;
        }
        return -1;
    }

    static void print() {
        for (int y = 0; y < H; y++) {
            System.out.println(map[y]);
        }
    }
}
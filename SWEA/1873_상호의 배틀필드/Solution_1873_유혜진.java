import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1873_유혜진 {
    // 맵 크기 및 정보
    static int H, W;
    static char[][] map;
    
    // 현재 전차 위치 및 바라보는 방향 (0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽)
    static int tankY, tankX, tankDir;
    
    // 방향 벡터: 상(0), 하(1), 좌(2), 우(3)
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[] tankShape = {'^', 'v', '<', '>'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];

            // 맵 입력 받기 및 전차 초기 위치/방향 찾기
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                    // 전차 문자인 경우 위치 저장
                    if (map[i][j] == '^') { tankY = i; tankX = j; tankDir = 0; }
                    else if (map[i][j] == 'v') { tankY = i; tankX = j; tankDir = 1; }
                    else if (map[i][j] == '<') { tankY = i; tankX = j; tankDir = 2; }
                    else if (map[i][j] == '>') { tankY = i; tankX = j; tankDir = 3; }
                }
            }

            int N = Integer.parseInt(br.readLine().trim()); // 명령 개수
            String commands = br.readLine(); // 커맨드 문자열

            // 명령어 시뮬레이션 수행
            for (int i = 0; i < N; i++) {
                char cmd = commands.charAt(i);
                processCommand(cmd);
            }

            // 결과 출력
            System.out.print("#" + tc + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.append(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    // 커맨드 처리 함수
    static void processCommand(char cmd) {
        if (cmd == 'U') {
            moveTank(0);
        } else if (cmd == 'D') {
            moveTank(1);
        } else if (cmd == 'L') {
            moveTank(2);
        } else if (cmd == 'R') {
            moveTank(3);
        } else if (cmd == 'S') {
            shoot();
        }
    }

    // 전차 이동 처리
    static void moveTank(int dir) {
        tankDir = dir; // 방향 변경
        map[tankY][tankX] = tankShape[dir]; // 맵에 전차 모양 업데이트

        int ny = tankY + dy[dir];
        int nx = tankX + dx[dir];

        // 맵 범위 안이고, 평지('.')인 경우에만 이동
        if (ny >= 0 && ny < H && nx >= 0 && nx < W && map[ny][nx] == '.') {
            map[tankY][tankX] = '.'; // 기존 위치는 평지로 변경
            tankY = ny;
            tankX = nx;
            map[tankY][tankX] = tankShape[dir]; // 새 위치에 전차 배치
        }
    }

    // 포탄 발사 처리
    static void shoot() {
        int sy = tankY + dy[tankDir];
        int sx = tankX + dx[tankDir];

        // 포탄이 맵 밖으로 나가거나 장애물을 만날 때까지 직진
        while (sy >= 0 && sy < H && sx >= 0 && sx < W) {
            // 벽돌 벽(*)을 만나면 파괴되어 평지(.)가 되고 포탄 소멸
            if (map[sy][sx] == '*') {
                map[sy][sx] = '.';
                break;
            }
            // 강철 벽(#)을 만나면 포탄만 소멸 (벽은 안 깨짐)
            if (map[sy][sx] == '#') {
                break;
            }
            // 평지(.)나 물(~)은 포탄이 통과함
            sy += dy[tankDir];
            sx += dx[tankDir];
        }
    }
}
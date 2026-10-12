import java.util.Scanner;
 
public class Solution {
 
    // 상, 우, 하, 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
 
    static int N;
    static int[][] board;
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
 
            N = sc.nextInt();
            board = new int[N][N];
 
            // 맵 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
 
            int answer = 0;
 
            // 모든 빈칸에서 시작
            for (int sx = 0; sx < N; sx++) {
                for (int sy = 0; sy < N; sy++) {
 
                    if (board[sx][sy] != 0) {
                        continue;
                    }
 
                    // 4방향으로 시작
                    for (int startDir = 0; startDir < 4; startDir++) {
 
                        int x = sx;
                        int y = sy;
                        int direction = startDir;
                        int score = 0;
 
                        while (true) {
 
                            int nx = x + dx[direction];
                            int ny = y + dy[direction];
 
                            // -----------------------------
                            // 벽
                            // -----------------------------
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
 
                                // 지금까지의 경로를 그대로 되돌아감
                                // 180도 반사되는 벽을 만났을때 지금까지 온 경로를 그대로 역순으로 돌아가기 때문에 점수 계산 후 마무리
                                score = score * 2 + 1;
 
                                break;
                            }
 

 
                            // -----------------------------
                            // 시작점
                            // -----------------------------
                            if (x == sx && y == sy) {
                                break;
                            }
 
                            // -----------------------------
                            // 블랙홀
                            // -----------------------------
                            if (board[x][y] == -1) {
                                break;
                            }
 
                            int block = board[x][y];
 
                            // -----------------------------
                            // 1번 블록
                            // -----------------------------
                            if (block == 1) {
 
                                if (direction == 0) {
                                    // 상 -> 하
                                    score = score * 2 + 1;
                                    break;
 
                                } else if (direction == 1) {
                                    // 우 -> 좌
                                    score = score * 2 + 1;
                                    break;
 
                                } else if (direction == 2) {
                                    // 하 -> 우
                                    direction = 1;
                                    score++;
 
                                } else {
                                    // 좌 -> 상
                                    direction = 0;
                                    score++;
                                }
                            }
 
                            // -----------------------------
                            // 2번 블록
                            // -----------------------------
                            else if (block == 2) {
 
                                if (direction == 0) {
                                    // 상 -> 우
                                    direction = 1;
                                    score++;
 
                                } else if (direction == 1) {
                                    // 우 -> 좌
                                    score = score * 2 + 1;
                                    break;
 
                                } else if (direction == 2) {
                                    // 하 -> 상
                                    score = score * 2 + 1;
                                    break;
 
                                } else {
                                    // 좌 -> 하
                                    direction = 2;
                                    score++;
                                }
                            }
 
                            // -----------------------------
                            // 3번 블록
                            // -----------------------------
                            else if (block == 3) {
 
                                if (direction == 0) {
                                    // 상 -> 좌
                                    direction = 3;
                                    score++;
 
                                } else if (direction == 1) {
                                    // 우 -> 하
                                    direction = 2;
                                    score++;
 
                                } else if (direction == 2) {
                                    // 하 -> 상
                                    score = score * 2 + 1;
                                    break;
 
                                } else {
                                    // 좌 -> 우
                                    score = score * 2 + 1;
                                    break;
                                }
                            }
 
                            // -----------------------------
                            // 4번 블록
                            // -----------------------------
                            else if (block == 4) {
 
                                if (direction == 0) {
                                    // 상 -> 하
                                    score = score * 2 + 1;
                                    break;
 
                                } else if (direction == 1) {
                                    // 우 -> 상
                                    direction = 0;
                                    score++;
 
                                } else if (direction == 2) {
                                    // 하 -> 좌
                                    direction = 3;
                                    score++;
 
                                } else {
                                    // 좌 -> 우
                                    score = score * 2 + 1;
                                    break;
                                }
                            }
 
                            // -----------------------------
                            // 5번 블록
                            // -----------------------------
                            else if (block == 5) {
 
                                // 어떤 방향이든 180도 반전
                                score = score * 2 + 1;
 
                                break;
                            }
 
                            // -----------------------------
                            // 웜홀
                            // -----------------------------
                            else if (block >= 6 && block <= 10) {
 
                                boolean found = false;
 
                                // 같은 번호의 다른 웜홀 찾기
                                for (int i = 0; i < N; i++) {
 
                                    for (int j = 0; j < N; j++) {
 
                                        if (board[i][j] == block
                                                && (i != x || j != y)) {
 
                                            x = i;
                                            y = j;
 
                                            found = true;
                                            break;
                                        }
                                    }
 
                                    if (found) {
                                        break;
                                    }
                                }
 
                                // 웜홀에서는 방향을 바꾸지 않는다.
                            }
                        }
 
                        answer = Math.max(answer, score);
                    }
                }
            }
 
            System.out.println("#" + tc + " " + answer);
        }
 
        sc.close();
    }
}
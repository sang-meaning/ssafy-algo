import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int N;
    static char[][] board;
    static int[][] count;
    static boolean[][] visited;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();

            board = new char[N][N];
            count = new int[N][N];
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                board[r] = sc.next().toCharArray();
            }

            // 각 칸의 주변 8방향 지뢰 개수 계산
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '*') continue;

                    for (int d = 0; d < 8; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            continue;
                        }

                        if (board[nr][nc] == '*') {
                            count[r][c]++;
                        }
                    }
                }
            }

            int answer = 0;

            // 주변 지뢰가 0개인 칸부터 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.'
                            && !visited[r][c]
                            && count[r][c] == 0) {
                        bfs(r, c);
                        answer++;
                    }
                }
            }

            // 자동으로 열리지 않은 칸은 하나씩 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == '.' && !visited[r][c]) {
                        answer++;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }

        sc.close();
    }

    static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int d = 0; d < 8; d++) {
                int nr = current[0] + dr[d];
                int nc = current[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }

                if (board[nr][nc] == '*' || visited[nr][nc]) {
                    continue;
                }

                // 인접한 숫자 칸도 함께 열린다.
                visited[nr][nc] = true;

                // 주변 지뢰가 0개인 칸만 계속 확장한다.
                if (count[nr][nc] == 0) {
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
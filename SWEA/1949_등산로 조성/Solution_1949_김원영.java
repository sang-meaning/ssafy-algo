import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static int[][] map;
    static boolean[][] visited;

    static int N;
    static int K;
    static int maxHeight;
    static int max;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static List<int[]> point = new ArrayList<>();

    static void dfs(int r, int c, int length, boolean cut) {

        max = Math.max(max, length);

        for(int i=0; i<4; i++) {

            int next_r = r + dr[i];
            int next_c = c + dc[i];

            if(next_r < 0 || next_r >= N || next_c < 0 || next_c >= N) {
                continue;
            }

            if(visited[next_r][next_c]) {
                continue;
            }

            // 그냥 이동 가능한 경우
            if(map[next_r][next_c] < map[r][c]) {

                visited[next_r][next_c] = true;

                dfs(next_r, next_c, length+1, cut);

                visited[next_r][next_c] = false;
            }

            // 그냥은 못 가지만 아직 공사를 안 쓴 경우
            else if(!cut) {

                // 최대 K만큼 깎았을 때 현재 높이보다 낮아지는지 확인
                if(map[next_r][next_c] - K < map[r][c]) {

                    int temp = map[next_r][next_c];

                    // 현재 높이보다 딱 1 낮게 깎기
                    map[next_r][next_c] = map[r][c] - 1;

                    visited[next_r][next_c] = true;

                    dfs(next_r, next_c, length+1, true);

                    visited[next_r][next_c] = false;

                    // 원상복구
                    map[next_r][next_c] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            N = sc.nextInt();
            K = sc.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];

            maxHeight = 0;
            max = 0;
            point.clear();

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {

                    map[i][j] = sc.nextInt();

                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            // 가장 높은 봉우리 저장
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {

                    if(map[i][j] == maxHeight) {
                        point.add(new int[] {i, j});
                    }
                }
            }

            // 가장 높은 봉우리들에서 각각 시작
            for(int i=0; i<point.size(); i++) {

                int r = point.get(i)[0];
                int c = point.get(i)[1];

                visited[r][c] = true;

                dfs(r, c, 1, false);

                visited[r][c] = false;
            }

            System.out.println("#" + tc + " " + max);
        }

        sc.close();
    }
}
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    static int N;
    static int[][] map;
    static int[][] dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];

            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0 || nr>=N || nc<0 || nc>=N) {
                    continue;
                }

                int cost = dist[r][c] + map[nr][nc];

                if(cost < dist[nr][nc]) {
                    dist[nr][nc] = cost;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];
            dist = new int[N][N];

            for(int i=0; i<N; i++) {
                String line = sc.next();

                for(int j=0; j<N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            for(int i=0; i<N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            bfs();

            System.out.println("#" + tc + " " + dist[N-1][N-1]);
        }
    }
}
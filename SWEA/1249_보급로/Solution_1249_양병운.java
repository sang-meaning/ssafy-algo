import java.util.*;
import java.io.*;
class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int endX, endY, N;
    static int[][] dist;
    static int[][] matrix;
    static int INF = Integer.MAX_VALUE/2;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            endX = N;
            endY = N;
            matrix = new int[N][N];
            dist = new int[N][N];
            for(int i=0; i<N; i++) Arrays.fill(dist[i], INF);
            dist[0][0] = matrix[0][0];
            for(int i=0; i<N; i++){
                String[] line = br.readLine().split("");
                for(int j=0; j<N; j++){
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{0, 0});
            while(!queue.isEmpty()){
                int[] q = queue.poll();
                int x = q[0];
                int y  = q[1];
                for(int i=0; i<4; i++){
                    int nx = x+dx[i];
                    int ny = y+dy[i];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    int depth = matrix[nx][ny];
                    if(dist[x][y]+depth >= dist[nx][ny]) continue;
                    dist[nx][ny] = dist[x][y]+depth;
                    if(nx == N-1 && ny == N-1) break;
                    queue.add(new int[]{nx, ny});
                }
            }
            System.out.println("#"+test_case+" "+dist[N-1][N-1]);
        }
    }
}
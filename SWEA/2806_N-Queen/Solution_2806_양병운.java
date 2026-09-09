import java.util.*;
import java.io.*;
class Solution {
    static int N, cnt;
    static int[][] matrix;
    static int[][] visited;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            matrix = new int[N][N];
            visited = new int[N][N];
            cnt = 0;
            dfs(0);
            System.out.println("#"+test_case+" "+cnt);
        }
    }
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void QueenVisit(int x, int y){
        visited[x][y]++;
        for(int len=0; len<N; len++){
            for(int dir=0; dir<8; dir++){
                int nx = x+(dx[dir]*len);
                int ny = y+(dy[dir]*len);
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                visited[nx][ny]++;
            }    
        }
    }
    public static void QueenUnvisit(int x, int y){
        visited[x][y]--;
        for(int len=0; len<N; len++){
            for(int dir=0; dir<8; dir++){
                int nx = x+(dx[dir]*len);
                int ny = y+(dy[dir]*len);
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                visited[nx][ny]--;
            }    
        }
    }
    public static void dfs(int depth){
        if(depth == N){
            cnt++;
            return;
        }
        for(int col=0; col<N; col++){
            if(visited[depth][col]>0) continue;
            QueenVisit(depth, col);
            dfs(depth+1);
            QueenUnvisit(depth, col);
        }
    }
}
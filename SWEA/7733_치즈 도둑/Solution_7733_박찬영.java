import java.io.*;
import java.util.*;
 
public class Solution {
    static int N;
    static int[][] map;
    static boolean[][] visited;
     
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); 
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
             
            int maxTaste = 0; 
 
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] > maxTaste) {
                        maxTaste = map[r][c];
                    }
                }
            }
 
            int maxPieces = 1;
 
            for (int day = 0; day <= maxTaste; day++) {
                visited = new boolean[N][N]; 
                int currentPieces = 0;        
 
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (map[r][c] > day && !visited[r][c]) {
                            currentPieces++;     
                            dfs(r, c, day);      
                        }
                    }
                }
 
                if (currentPieces > maxPieces) {
                    maxPieces = currentPieces;
                }
            }
 
            System.out.println("#" + tc + " " + maxPieces);
        }
    }
 
    static void dfs(int r, int c, int day) {
        visited[r][c] = true; 
 
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
 
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                continue;
            }
 
            if (map[nr][nc] > day && !visited[nr][nc]) {
                dfs(nr, nc, day);
            }
        }
    }
}
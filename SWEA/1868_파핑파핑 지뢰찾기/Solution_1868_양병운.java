import java.util.*;
import java.io.*;
class Solution {
    static int N;
    static String[][] mines;
    static int[][] matrix;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            mines = new String[N][N];
            matrix = new int[N][N];
            for(int i=0; i<N; i++){
                String[] split = br.readLine().split("");
                Arrays.fill(matrix[i], Integer.MAX_VALUE);
                for(int j=0; j<N; j++){
                    mines[i][j] = split[j];
                    if(mines[i][j].equals("*")) matrix[i][j] = -1;
                }
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == Integer.MAX_VALUE && countMine(i, j) == 0) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == Integer.MAX_VALUE) cnt++;
                }
            }
            System.out.println("#"+test_case+" "+cnt);
		}
	}
    public static int countMine(int x, int y) {
        int mine = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (mines[nx][ny].equals("*")) mine++;
        }
        return mine;
    }
    public static boolean checkVisit(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++) 
                if(matrix[i][j] == Integer.MAX_VALUE) return false;
        }
        return true;
    }
    
	static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
	static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    public static void dfs(int x, int y){
        if(matrix[x][y] != Integer.MAX_VALUE) return;
        int mine = 0;
        for(int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(mines[nx][ny].equals("*")) mine++;
        }
        matrix[x][y] = mine;
        if(mine != 0) return;
        for(int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            dfs(nx, ny);
        }
    }
}
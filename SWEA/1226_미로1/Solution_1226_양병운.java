import java.util.*;
import java.io.*;
class Solution {
    static int size = 16;
    static int[][] matrix;
    static boolean[][] visited;
    static int found;
    static int startX, startY;
    static int endX, endY;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {
            int num = Integer.parseInt(br.readLine());
			matrix = new int[size][size];
            visited = new boolean[size][size];
            found = 0;
            for(int i=0; i<16; i++){
                String[] line = br.readLine().split("");
                for(int j=0; j<16; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                    if(matrix[i][j] == 1){
                        visited[i][j] = true;
                    } else if(matrix[i][j] == 2) {
                        startX = i;
                        startY = j;
                    }else if(matrix[i][j] == 3){
                        endX = i;
                        endY = j;
                    }
                }
            }
            dfs(startX, startY);
            System.out.println("#"+num+" "+found);
		}
	}
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void dfs(int x, int y){
        visited[x][y] = true;
        if(x == endX && y == endY){
            found = 1;
            return;
        }
        if(found==1) return;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= size || ny >= size || visited[nx][ny]) continue;
            dfs(nx, ny);
        }
    }
}
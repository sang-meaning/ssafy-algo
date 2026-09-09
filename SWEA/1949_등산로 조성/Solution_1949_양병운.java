import java.util.*;
import java.io.*;
class Solution {
    static int N, K, max;
    static int[][] matrix;
    static boolean[][] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            /**
            	봉우리부터 무조건 낮은 곳으로, 세로 혹은 가로
                긴 등산로를 만들기 위해 한 곳을 K만큼 깎을 수 있다.
            */
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            matrix = new int[N][N];
            visited = new boolean[N][N];
            int start = 0; 
            max = 0;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    if(matrix[i][j] > start) start = matrix[i][j];
                }
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(matrix[i][j] == start && K > 0) max = Math.max(max, dfs(i, j, true));
                    else if(matrix[i][j] == start) max = Math.max(max, dfs(i, j, false));
                }
            }
            System.out.println("#"+test_case+" "+max);
		}
	}
    static int[] dx = {0 ,0 , 1, -1};
    static int[] dy = {1, -1, 0, 0};
	static int dfs(int x, int y, boolean canCut){
        int result = 1;
        visited[x][y] = true;
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(visited[nx][ny]) continue;
            if(matrix[nx][ny] < matrix[x][y]) result = Math.max(result, 1 + dfs(nx, ny, canCut));
            else if(canCut){
                if(matrix[nx][ny] - K < matrix[x][y]){
                    int temp = matrix[nx][ny];
                    matrix[nx][ny] = matrix[x][y] - 1;
                    result = Math.max(result, 1 + dfs(nx, ny, false));
                    matrix[nx][ny] = temp;
                }
            }
        }
        visited[x][y] = false;
        return result;
    }
}
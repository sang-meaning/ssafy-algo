import java.util.*;
import java.io.*;
class Solution {
    static int N, sum;
    static int[][] matrix;
    static boolean[] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            matrix = new int[N][N];
            visited = new boolean[N];
            for(int i=0; i<N; i++) matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            sum = 0;
            dfs(N/2, 0);
            System.out.println("#"+test_case+" "+sum);
		}
	}
    public static void dfs(int row, int rem) {
        if(row < 0 || row == N) return;
        for(int col=rem; col<N-rem; col++){
            sum += matrix[row][col];
        }
        visited[row] = true;
        if(row-1 >= 0 && !visited[row-1]) dfs(row-1, rem+1);
        if(row+1 < N && !visited[row+1]) dfs(row+1, rem+1);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static int N, answer;
	static int[] queen;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			N = Integer.parseInt(br.readLine());
			
			queen = new int[N];
			answer = 0;
			dfs(0);
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");

		}
		
		System.out.print(sb);
	}
	
	static void dfs(int row) {
		if (row == N) {
			answer++;
			return;
		}
		
		// 열 후보를 전부 시도
		for (int col = 0; col < N; col++) {
			
			if (isPossible(row, col)) {
				queen[row] = col;
				dfs(row + 1);
			}
		}
	}
	
	static boolean isPossible(int row, int col) {
		
		// 이전 행의 모든 퀸과 비교
		for (int i = 0; i < row; i++) {
			
			// 같은 열
			if (queen[i] == col) return false;
			
			// 같은 대각선
			if (Math.abs(row - i) == Math.abs(col - queen[i])) return false;
		}
		
		return true;
	}
}
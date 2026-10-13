import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] V = new int[N+1];
			int[] C = new int[N+1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				V[i] = Integer.parseInt(st.nextToken());  // 부피
				C[i] = Integer.parseInt(st.nextToken());  // 가치
			}
			
			int[][] dp = new int[N+1][K+1];
			for (int i = 1; i <= N; i++) {  // 고려할 물건 개수
				for (int j = 1; j <= K; j++) {  // 현재 가방 용량
					
					if (V[i] <= j) {
						dp[i][j] = Math.max(dp[i-1][j], C[i] + dp[i-1][j - V[i]]);
					} else {
						dp[i][j] = dp[i-1][j];
					}
										
				}
			}
			
			int answer = dp[N][K];
			
			sb.append("#").append(test_case).append(" ")
			  .append(answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
}
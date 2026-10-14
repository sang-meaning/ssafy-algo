import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[n+1][100000];

			for(int i = 1; i<= n; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				
				// 정하는 부분 -> dp[i][부피]에서 최대 -> 2가지 (나 없음, 나 있음)
				for(int j= 1; j <= k; j++) {
					// 나 없음
					dp[i][j] = dp[i-1][j];
					
					// 나 있음, 둘중 최대 가치 저장
					if(j-v >= 0) {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-v] + c);
					}
				}
				
				
			}
			
			System.out.println("#" + testcase + " " + dp[n][k]);
		}
	
	}
}
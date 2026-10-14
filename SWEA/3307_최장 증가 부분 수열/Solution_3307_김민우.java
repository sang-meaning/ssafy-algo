import java.util.*;
import java.io.*;

public class Solution_3307_±è¹Î¿ì {
	static int[] dp;
	static int[] num;
	static int T, N;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			num = new int[N];
			dp = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			
			for(int i = 0; i < N; i++) {
				dp[i] = 1;
				for(int j = 0; j < i; j++) {
					if(num[i] > num[j])
						dp[i] = Math.max(dp[i], dp[j]+1);
				}
				ans = Math.max(ans, dp[i]);
			}
			
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}

}

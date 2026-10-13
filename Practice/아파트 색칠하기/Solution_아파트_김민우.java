import java.io.*;
import java.util.*;

public class Solution_아파트_김민우 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][2];
		// 0 : i층 아파트 중 마지막 색깔이 노란색
        // 1 : i층 아파트 중 마지막 색깔이 파란색
        dp[1][0] = 1;
		dp[1][1] = 1;
		
        for(int i = 2; i <= N; i++){
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        int ans = dp[N][0] + dp[N][1];
		System.out.println(ans);
	}
}


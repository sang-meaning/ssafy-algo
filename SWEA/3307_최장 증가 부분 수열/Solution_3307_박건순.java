import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			long[] arr = new long[N + 1];
			for (int n = 1; n <= N; n++) {
				arr[n] = Integer.parseInt(st.nextToken());
			}
			long maxDist = -1;
			long[] dp = new long[N + 1];
			for (int i = 1; i <= N; i++) {
				dp[i] = 1;
				for (int j = 1; j < i; j++) {
					if (arr[j] < arr[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
				maxDist = Math.max(dp[i], maxDist);
			}
			System.out.println(String.format("#%d %d", t, maxDist));
		}
	}
}